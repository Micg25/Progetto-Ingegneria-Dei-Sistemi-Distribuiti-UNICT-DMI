package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    Response weatherService(Request request) throws RemoteException;
}
