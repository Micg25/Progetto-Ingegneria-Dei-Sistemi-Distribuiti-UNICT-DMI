package clientpart;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sharedpart.Service;

public class Main {

    public static void main(String[] args) {
        Service service = findService();

        Client alice = new Client("Alice", service);
        Client bob = new Client("Bob", service);
        Client dan = new Client("Dan", service);

        alice.listBooks("Microservices");
        alice.showRecent();

        bob.listBooks("Refactoring");
        bob.listBooks("Head");
        bob.showRecent();

        alice.showRecent();

        bob.buyBook("Design Patterns");
        bob.checkout();

        alice.buyBook("API Design Patterns");
        alice.checkout();

        dan.listBooks("Design");
        dan.showRecent();
        dan.buyBook("Design Patterns");
        dan.checkout();
    }

    private static Service findService() {
        Service service = null;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            service = (Service) registry.lookup("CartService");
            System.out.println("Service found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }
}
