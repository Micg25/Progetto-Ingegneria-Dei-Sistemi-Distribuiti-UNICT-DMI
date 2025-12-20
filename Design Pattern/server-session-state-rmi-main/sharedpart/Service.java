package sharedpart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Service is the interface for the book store service. It defines the methods
 * that can be invoked by the client.
 */
public interface Service extends Remote {

    List<String> findBooks(String keyword, String id) throws RemoteException;

    List<String> inCart(String title, String id) throws RemoteException;

    List<String> getRecent(String id) throws RemoteException;

    List<Double> getPriceCart(String id) throws RemoteException;

    void checkout(String id) throws RemoteException;
}