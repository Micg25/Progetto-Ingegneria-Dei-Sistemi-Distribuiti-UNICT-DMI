package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Service extends Remote {
    List<Response> weatherService(List<Request> batch) throws RemoteException;
}
