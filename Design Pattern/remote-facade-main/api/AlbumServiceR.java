package api;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dto.AlbumDTO;

/**
 * AlbumServiceR is a Remote Facade interface, it is a sub-interface of Remote
 * as RMI needs it.
 */

public interface AlbumServiceR extends Remote {
    public AlbumDTO getAlbum(String id) throws RemoteException;

    public void createAlbum(String id, AlbumDTO dto) throws RemoteException;

    public void updateAlbum(String id, AlbumDTO dto) throws RemoteException;
}
