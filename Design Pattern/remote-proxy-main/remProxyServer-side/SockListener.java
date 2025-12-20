import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/** SockListener has role Receiver for design pattern Forwarder-Receiver */
public class SockListener {
	private static final int localport = 1050;
	private ServerSocket svsock;
	private Socket clsock = null;
	private BufferedReader fromClient;
	private PrintWriter toClient;
	private ServerProxy p;

	public static void main(String[] args) throws IOException {
		System.out.println("SockListener: listening for a connection");
		SockListener listener = new SockListener();
		listener.p = new ServerProxy(listener);
		listener.runListener();
	}

	/** Open a socket and listen from a pipe */
	private void runListener() {
		try {
			svsock = new ServerSocket(localport);
			clsock = svsock.accept();
			fromClient = new BufferedReader(new InputStreamReader(clsock.getInputStream()));
			toClient = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clsock.getOutputStream())), true);
			while (true) {
				// get requests from the network sequentially
				String request = getFromClient();
				if (!p.handleRequest(request))
					break;
			}
			fromClient.close();
			toClient.close();
			clsock.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void replyToClient(String s) {
		System.out.println("SockListener: send reply");
		toClient.println(s);
		delay();
	}

    /** Read from the socket channel */
	public String getFromClient() {
		System.out.println("\nSockListener: waiting to receive");
		String result = null;
		delay();
		try {
			result = fromClient.readLine();
		} catch (IOException e) {
			System.err.println(e);
		}
		return result;
	}

	private static void delay() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}