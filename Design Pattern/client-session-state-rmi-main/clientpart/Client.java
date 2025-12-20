package clientpart;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;

import sharedpart.Service;
import sharedpart.StatusDTO;

/**
 * Client is a class on the client-side. Client holds the state of the session.
 */
public class Client {
    private final String name;
    private final Service service;

    private StatusDTO dto = new StatusDTO(new ArrayList<>(), new HashSet<>(), new HashSet<>(), new ArrayList<>());

    public Client(String name, Service serv) {
        this.name = name;
        service = serv;
    }

    public void listBooks(String title) {
        System.out.println("Client: " + name + " asks service to search " + title);
        try {
            dto = service.findBooks(title, dto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        dto.result().forEach(s -> System.out.println(" " + s));
    }

    public void showRecent() {
        System.out.println("Client: " + name + " asks service the recent searches");
        dto.recent().forEach(s -> System.out.println(" " + s));
    }

    public void showCart() {
        System.out.println("Client: " + name + " asks service the cart");
        dto.cart()
                .forEach(s -> System.out.println(" " + s));
    }

    public void buyBook(String title) {
        System.out.println("Client: " + name + " asks service to insert in cart");
        try {
            dto = service.inCart(title, dto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("Client: " + name + " " + dto.cart().size() + " book(s), total " + getTotal());
        showCart();
    }

    private double getTotal() {
        return dto.cartPrice().stream().reduce(0d, Double::sum);
    }

    public void checkout() {
        System.out.println(
                "Client: " + name + " asks service to buy " + dto.cart().size() + " book(s) for " + getTotal());
        try {
            dto = service.checkout(dto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
