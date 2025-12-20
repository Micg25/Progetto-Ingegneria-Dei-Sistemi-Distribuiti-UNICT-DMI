package serverpart;

import java.rmi.RemoteException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sharedpart.Service;

/**
 * ServiceImpl is the class on the server, it is a Remote Facade, whose
 * methods are invoked using RMI. ServiceImpl holds the status of the
 * sessions.
 */

public class ServiceImpl implements Service {
    private final BookTable books = new BookTable();
    private final DiskSer disk = new DiskSer();
    private final Map<String, Status> sessions = new HashMap<>();
    private final int expireTime = 200; // millis

    /**
     * @param keyword is a portion of the title to be searched
     * @param id      is an identifier for the requesting user
     * @return holds the results of the search
     */
    @Override
    public List<String> findBooks(String keyword, String id) throws RemoteException {
        Status status = getSession(id);
        List<Book> result = books.getList(keyword);
        status.recent().addAll(result);
        return result.stream().map(b -> b.title()).toList();
    }

    /**
     * @param title is the whole title of the book
     * @param id    is an identifier for the user
     */
    @Override
    public List<String> inCart(String title, String id) throws RemoteException {
        Status status = getSession(id);
        Book book = books.getBook(title);
        if (book != null)
            status.cart().add(book);
        return status.cart().stream().map(b -> b.title()).toList();
    }

    @Override
    public List<String> getRecent(String id) throws RemoteException {
        return getSession(id).recent().stream().map(b -> b.title()).toList();
    }

    @Override
    public List<Double> getPriceCart(String id) throws RemoteException {
        return getSession(id).cart().stream().map(b -> books.getPrice(b)).toList();
    }

    @Override
    public void checkout(String id) throws RemoteException {
        // login
        // payment
        // process order
        getSession(id).cart().clear();
    }

    /*
     * Find the Status object for the given id, which could be held in sessions or
     * could be in the disk.
     */
    private Status getSession(String id) {
        Status s = sessions.get(id);
        if (s != null)
            return refresh(id, s);
        dropSessions();
        s = disk.read(id);
        if (s != null) {
            removeDoubleBooks(s); // fix the effect of deserialization
            return refresh(id, s);
        }
        s = new Status(new ArrayList<>(), new HashSet<>(), Instant.now());
        sessions.put(id, s);
        return s;
    }

    private void removeDoubleBooks(Status s) {
        List<Book> cart = s.cart();
        List<Book> realBooks = new ArrayList<>();
        for (Book b : cart) {
            Book realB = books.getBook(b.title());
            if (realB != b) {
                realBooks.add(realB);
                System.out.println("[SerImpl] replaced instance of " + b.title());
            } else {
                System.out.println("[SerImpl] *not* replaced " + b.title());
                realBooks.add(b);
            }
        }
        s.cart().clear();
        s.cart().addAll(realBooks);
    }

    private Status refresh(String id, Status s) {
        Status refreshed = new Status(s.cart(), s.recent(), Instant.now());
        sessions.put(id, refreshed);
        return refreshed;
    }

    /** Check and save expired sessions */
    private void dropSessions() {
        List<Entry<String, Status>> expired = sessions.entrySet().stream()
                .filter(e -> isExpired(e.getValue()))
                .map(e -> saveToDisk(e))
                .toList();

        if (expired.size() > 0)
            sessions.entrySet().removeAll(expired);
    }

    private boolean isExpired(Status s) {
        return s.timestamp().plus(expireTime, ChronoUnit.MILLIS).isBefore(Instant.now());
    }

    private Entry<String, Status> saveToDisk(Entry<String, Status> e) {
        System.out.println("[SerImpl] save to disk ");
        disk.write(e.getKey(), e.getValue());
        return e;
    }
}
