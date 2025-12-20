package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** Service defines remote methods for profile management with idempotency */
public interface Service extends Remote {
    boolean updateProfile(String msgId, String userId, Profile profile) throws RemoteException;

    Profile getProfile(String msgId, String userId) throws RemoteException;
}
