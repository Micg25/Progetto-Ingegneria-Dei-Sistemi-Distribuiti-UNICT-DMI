public class Client { // Client
	public static void main(String[] args) {
		System.out.println("Client: create first Book instance");
		
        Volume b = new LocalBook();

		System.out.println("\nClient: call getText");
		System.out.println("\nClient: received: " + b.getText());
        System.out.println("\nClient: received: " + b.getText());

        System.out.println("\nClient: call append");
		b.append("Hello world.");

        System.out.println("\nClient: received: " + b.getText());
        
		System.out.println("\nClient: create second Book instance");
		Volume b2 = new LocalBook();

        System.out.println("\nClient: call getText");
		System.out.println("\nClient: received: " + b2.getText());
		System.out.println("\nClient: call append");

        b2.append("Hello world.");
 
		// close connections
		SockCommunicator.closeConnections();
	}
}