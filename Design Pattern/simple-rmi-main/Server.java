import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        try {
            // Create the remote object
            HelloImpl obj = new HelloImpl();
            // Export the remote object, and obtain a stub for it
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Start registry
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the stub to the registry
            registry.rebind("HelloService", stub);

            System.out.println("RMI Server ready on port 1099");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
