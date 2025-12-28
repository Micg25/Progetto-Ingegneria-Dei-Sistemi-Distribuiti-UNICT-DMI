package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
public interface SpotifyService extends Remote{
    List<StreamRecordDTO> getSongsByYear(int year) throws RemoteException;
    Token login(String username) throws RemoteException;
}
