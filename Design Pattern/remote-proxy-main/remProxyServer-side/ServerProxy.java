import java.util.HashMap;
import java.util.Map;

/**
 * ServerProxy has role Peer for design pattern Forwarder-Receiver. ServerProxy
 * receives requests encoded as string parameters and then calls methods on
 * RealSubject Book
 */
public class ServerProxy {
    // references holds the pair id-reference
    private Map<Integer, Book> references = new HashMap<>();
    private SockListener channel;

    public ServerProxy(SockListener l) {
        channel = l;
    }

    /** Convert strings encoding requests to method calls */
    public boolean handleRequest(String request) {
        System.out.println("ServerProxy: just received a request " + request);
        String[] params = request.split(",");

        if (params.length != 3)
            return false;

        System.out.println("ServerProxy: id object " + params[0] + " " + params[1]);

        if (params[0].equals("Book"))
            return handleBook(params);
        return false;
    }

    private boolean handleBook(String[] params) {
        int id = Integer.valueOf(params[1]);
        boolean reply = true;
        switch (params[2]) {
            case "create" -> create(id);
            case "getText" -> getText(id);
            case "append" -> append(id);
            default -> reply = false;
        }
        return reply;
    }

    private void append(int id) {
        System.out.println("ServerProxy: append, reading from channel");
        String s = channel.getFromClient();
        System.out.println("ServerProxy: calling append");
        references.get(id).append(s);
    }

    private void getText(int id) {
        System.out.println("ServerProxy: getText, calling getText on object");
        String r = references.get(id).getText();
        System.out.println("ServerProxy: replying to client");
        channel.replyToClient(String.valueOf(id).concat(r));
    }

    private void create(int id) {
        System.out.println("ServerProxy: create, creating new instance");
        references.put(id, new Book());
    }
}