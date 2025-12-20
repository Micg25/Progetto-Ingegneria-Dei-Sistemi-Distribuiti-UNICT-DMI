/**
 * LocalBook has role RemoteProxy for design pattern Proxy and role Peer for
 * design pattern Forwarder-Receiver.
 */
public class LocalBook implements Volume {
    private SockCommunicator sc;
    private static int counter = 0; // shared counter of objects
    private String id; // identifier of the remote object
    // the id on the remote host is mapped to a corresponding instance

    public LocalBook() {
        System.out.println("LocalBook: start a connection");
        sc = SockCommunicator.geCommunicator("Book");
        sc.openConnection();
        System.out.println("LocalBook: send create command");
        id = String.valueOf(counter);
        counter++;
        sc.sendRequest("Book,".concat(id).concat(",create"));
    }

    @Override
    public String getText() {
        System.out.println("LocalBook: send getText command");
        sc.sendRequest("Book,".concat(id).concat(",getText"));
        String s = sc.getAnswer();
        if (s != null && s.startsWith(id))
            return s.substring(id.length());
        return null;
    }

    @Override
    public void append(String s) {
        System.out.println("LocalBook: send append command");
        sc.sendRequest("Book,".concat(id).concat(",append"));
        sc.sendRequest("Book,".concat(id).concat(",").concat(s));
    }
}