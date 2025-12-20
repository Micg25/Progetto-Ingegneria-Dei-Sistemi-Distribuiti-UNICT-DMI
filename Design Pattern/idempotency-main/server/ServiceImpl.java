package server;

import java.rmi.RemoteException;

import shared.Profile;
import shared.Service;

/** ServiceImpl implements the Service interface with idempotent methods */
public class ServiceImpl implements Service {
    private final MessageDB messageDB = new MessageDB();
    private final UserDB userDB = new UserDB();

    @Override
    public boolean updateProfile(String msgId, String userId, Profile profile) throws RemoteException {
        System.out.println("Received updateProfile request: msgId=" + msgId + ", userId=" + userId);
        if (invalidProfile(profile))
            return false;

        User user = userDB.findUser(userId);
        if (user == null) {
            System.out.println("User not found: " + userId);
            return false;
        }

        if (messageDB.findMessageUpdateProfile(msgId).isPresent()) {
            System.out.println("Duplicate message update ID: " + msgId);
            return false; // message already processed
        }

        User updatedUser = new User(user.userId(), profile.userName(), profile.email(), profile.phoneNumber(), user.passwordHash(), user.isAdmin(), user.createdAt(), user.updatedAt());
        userDB.updateUser(updatedUser);
        System.out.println("Updated user: " + profile.userId());

        messageDB.addMessageUpdate(new ProcessedMessage(msgId, java.time.Instant.now()));
        System.out.println("Processed profile update for user: " + profile.userId());
        return true;
    }

    @Override
    public Profile getProfile(String msgId, String userId) throws RemoteException {
        System.out.println("Received getProfile request: msgId=" + msgId + ", userId=" + userId);
        User user = userDB.findUser(userId);
        if (user == null) {
            System.out.println("User not found: " + userId);
            return null;
        }

        Profile p = messageDB.findMessageGetProfile(msgId);
        if (p != null) {
            System.out.println("Duplicate message ID: " + msgId);
            return p; // message already processed
        }
        p = new Profile(user.userId(), user.userName(), user.email(), user.phoneNumber());
        messageDB.addMessageProfile(new ProcessedMessage(msgId, java.time.Instant.now()), p);
        System.out.println("Retrieved profile for user: " + userId);
        return p;
    }

    private boolean invalidProfile(Profile profile) {
        return false;
    }

    public void fillUsers() {
        User u = new User("user-123", "Ciccio", "cic@email.com", "12-312-312", "hashedpwd", false,
                java.time.Instant.now().toString(), java.time.Instant.now().toString());
        userDB.addUser(u);
    }
}
