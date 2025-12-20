import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/** SockCommunicator has role Forwarder for design pattern Forwarder-Receiver */
public class SockCommunicator {
    private Socket sock = null;
    private BufferedReader inpipe = null;
    private PrintWriter outpipe = null;
    private String rmHost;
    private int port;
    private boolean connected = false;
    private static Map<String, SockCommunicator> comms = new HashMap<>();

    static {
        comms.put("Book", new SockCommunicator("localhost", 1050));
        // add more key (names of classes) and corresponding instances of
        // SockCommunicator that correspond to remote hosts and services connected to
        // ports (or other processes)
    }

    public static SockCommunicator geCommunicator(String s) {
        if (comms.containsKey(s))
            return comms.get(s);
        return null;
    }

    public static void closeConnections() {
        comms.values().forEach(s -> s.closeConnection());
    }

    public void openConnection() {
        if (connected)
            return;
        System.out.println("SockCommunicator: open connection");
        try {
            sock = new Socket(rmHost, port);
            inpipe = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            outpipe = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
            connected = true;
        } catch (UnknownHostException e) {
            System.err.println("Host unknown " + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Connection not working " + e);
            System.exit(1);
        }
        System.out.println("SockCommunicator: connection established");
    }

    public SockCommunicator(String host, int prt) {
        rmHost = host;
        port = prt;
    }

    public void sendRequest(String testo) {
        System.out.println("SockCommunicator: send request " + testo);
        outpipe.println(testo);
        delay();
    }

    public String getAnswer() {
        System.out.println("SockCommunicator: waiting to getAnswer");
        delay();
        try {
            return inpipe.readLine();
        } catch (IOException e) {
        }
        return null;
    }

    public void closeConnection() {
        sendRequest("close");
        try {
            outpipe.close();
            inpipe.close();
            sock.close();
            connected = false;
        } catch (IOException e) {
        }
    }

    private static void delay() {
        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
