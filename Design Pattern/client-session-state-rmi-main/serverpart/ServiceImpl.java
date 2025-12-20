package serverpart;

import java.rmi.RemoteException;
import java.util.List;

import sharedpart.Service;
import sharedpart.StatusDTO;

/**
 * ServiceImpl is the class on the server, it is a Remote Facade, whose
 * methods are invoked using RMI. ServiceImpl is stateless. The status of the
 * session is kept on the client side. Service updates the status and sends it
 * to the client.
 */

public class ServiceImpl implements Service {
    private final BookTable books = new BookTable();

    /**
     * @param keyword is a portion of the title to be searched
     * @param dto     is the instance holding the status of the session (cart,
     *                recent) and the results of the search.
     */
    @Override
    public StatusDTO findBooks(String keyword, StatusDTO dto) throws RemoteException {
        dto.result().clear();
        List<Book> l = books.getList(keyword);
        for (Book b : l)
            dto.result().add(b.title());
        dto.recent().addAll(dto.result());
        return dto;
    }

    /**
     * @param title is the whole title of the book
     * @param dto   is the instance holding the status of the session
     */
    @Override
    public StatusDTO inCart(String title, StatusDTO dto) throws RemoteException {
        Book book = books.getBook(title);
        if (book != null) {
            dto.cart().add(book.title());
            dto.cartPrice().add(books.getPrice(book));
        }
        return dto;
    }

    @Override
    public StatusDTO checkout(StatusDTO dto) throws RemoteException {
        // login
        // payment
        // process order
        dto.cart().clear();
        return dto;
    }
}
