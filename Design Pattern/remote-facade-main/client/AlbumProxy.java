package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import api.AlbumServiceR;
import dto.AlbumDTO;

/**
 * AlbumProxy is a Proxy that simplifies the Client, which need not know about
 * distribution.
 */
public class AlbumProxy implements AlbumService {
    // the name used for service look up
    // use the proper address of the remote host // 127.0.0.1
    private final String serviceName = "rmi://localhost:1099/AlbumService";
    private AlbumServiceR remote;

    public AlbumProxy() {
        try {
            System.out.println("Proxy: looking for the service in RMI registry");
            // get the instance from the RMI registry
            remote = (AlbumServiceR) Naming.lookup(serviceName);
        } catch (RemoteException e) {
            System.err.println(e);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (NotBoundException e) {
            System.err.println(e);
        }
    }

    @Override
    public AlbumDTO getAlbum(String id) {
        System.out.println("Proxy: in get album");
        try {
            return remote.getAlbum(id);
        } catch (RemoteException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public void createAlbum(String id, AlbumDTO dto) {
        System.out.println("Proxy: create remote album");
        try {
            remote.createAlbum(id, dto);
        } catch (RemoteException e) {
            System.err.println(e);
        }
    }

    @Override
    public void updateAlbum(String id, AlbumDTO dto) {
        System.out.println("Proxy: update remote album");
        try {
            remote.updateAlbum(id, dto);
        } catch (RemoteException e) {
            System.err.println(e);
        }
    }
}
