package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import shared.Profile;
import shared.Service;

/**
 * Main is the client performing service requests each with unique message IDs
 */
public class Main {
    private static Service service;
    private static String user = "user-123";

    public static void main(String[] args) {
        System.out.println("Client started");

        service = findService();
        if (service == null) {
            System.out.println("Could not find service, exiting.");
            return;
        }

        Profile p1 = new Profile(user, "Pippo", "pippo@example.com", "555-1234");

        sendUpdateProfile("msg-101", p1);
        sendUpdateProfile("msg-101", p1);
        sendRequestProfile("msg-102");
        sendRequestProfile("msg-102");
        sendRequestProfile("msg-103");
    }

    private static void sendUpdateProfile(String msgId, Profile profile) {
        try {
            boolean result = service.updateProfile(msgId, user, profile);
            System.out.println("Update result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendRequestProfile(String msgId) {
        try {
            Profile p = service.getProfile(msgId, user);
            System.out.println("Retrieved profile: " + p.userName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Service findService() {
        Service service = null;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            service = (Service) registry.lookup("ProfileService");
            System.out.println("Service found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }
}
