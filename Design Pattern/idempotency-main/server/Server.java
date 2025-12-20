package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import shared.Service;

/** Server sets up the RMI server and binds the Service implementation */
public class Server {
    public static void main(String[] args) {
        try {
            // Create the remote object
            ServiceImpl obj = new ServiceImpl();
            obj.fillUsers();
            // Export the remote object, and obtain a stub for it
            Service stub = (Service) UnicastRemoteObject.exportObject(obj, 0);

            // Start registry
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the stub to the registry
            registry.rebind("ProfileService", stub);

            System.out.println("[Server] RMI Server ready on port 1099");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
