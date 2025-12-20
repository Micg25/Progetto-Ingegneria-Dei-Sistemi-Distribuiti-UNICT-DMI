package serverpart;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import sharedpart.Service;

public class Server {
    public static void main(String[] args) {
        try {
            // Create the remote object
            ServiceImpl obj = new ServiceImpl();
            // Export the remote object, and obtain a stub for it
            Service stub = (Service) UnicastRemoteObject.exportObject(obj, 0);

            // Start registry
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the stub to the registry
            registry.rebind("CartService", stub);

            System.out.println("[Server] RMI Server ready on port 1099");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
