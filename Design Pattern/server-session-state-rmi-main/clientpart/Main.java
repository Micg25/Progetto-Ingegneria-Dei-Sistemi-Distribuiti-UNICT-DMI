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

        delay(250);

        bob.listBooks("Refactoring");
        bob.listBooks("Head");
        bob.showRecent();

        delay(250);

        alice.showRecent();

        bob.possiblyBuy("Design Patterns");
        // bob.checkout();

        alice.possiblyBuy("API Design Patterns");
        // alice.checkout();

        delay(250);

        dan.listBooks("Design");
        dan.showRecent();
        dan.possiblyBuy("Design Patterns");
        // dan.checkout();

        delay(250);
        alice.showRecent();
        dan.showRecent();
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

    private static void delay(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
