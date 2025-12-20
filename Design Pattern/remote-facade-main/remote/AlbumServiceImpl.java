package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import api.AlbumServiceR;
import dto.AlbumDTO;

/** Remote service implementation (as RMI), playing Remote Facade */
public class AlbumServiceImpl extends UnicastRemoteObject implements AlbumServiceR {

    public AlbumServiceImpl() throws RemoteException {
        super();
        System.out.println("AlbumServiceImpl: in constructor");
    }

    @Override
    public AlbumDTO getAlbum(String id) throws RemoteException {
        System.out.println("AlbumServiceImpl: get album request ...");
        return new AlbumAssembler().writeDTO(Registry.findAlbum(id));
    }

    @Override
    public void createAlbum(String id, AlbumDTO dto) throws RemoteException {
        System.out.println("AlbumServiceImpl: create album request ...");
        new AlbumAssembler().createAlbum(id, dto);
    }

    @Override
    public void updateAlbum(String id, AlbumDTO dto) throws RemoteException {
        System.out.println("AlbumServiceImpl: update album request ...");
        new AlbumAssembler().updateAlbum(id, dto);
    }

}