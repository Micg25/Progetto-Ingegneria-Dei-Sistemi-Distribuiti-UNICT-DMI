package sharedpart;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {

    /**
     * @param keyword is a portion of the title to be searched
     * @param dto     is the instance holding the status of the session (cart,
     *                recent) and the results of the search.
     */
    StatusDTO findBooks(String keyword, StatusDTO dto) throws RemoteException;

    /**
     * @param title is the whole title of the book
     * @param dto   is the instance holding the status of the session
     */
    StatusDTO inCart(String title, StatusDTO dto) throws RemoteException;

    StatusDTO checkout(StatusDTO dto) throws RemoteException;
}