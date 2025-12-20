package clientpart;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sharedpart.Service;

/**
 * Client is a class on the client-side. Client sends its hashedName to be identified by the server.
 */
public class Client {
    private final String name;
    private final String hashedName;
    private final Service service;

    public Client(String name, Service serv) {
        this.name = name + " " + Integer.toString(new Random().nextInt(1000));
        System.out.println("[Client] "+ this.name+" starts execution");
        this.hashedName = name+this.name.hashCode() + "";
        service = serv;
    }

    public void listBooks(String key) {
        List<String> result = new ArrayList<>();
        System.out.println("[Client] " + name + " asks service to search " + key);
        try {
            result = service.findBooks(key, hashedName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        result.forEach(s -> System.out.println(" " + s));
    }

    public void showRecent() {
        List<String> recent = new ArrayList<>();
        System.out.println("[Client] " + name + " asks service the recent searches");
        try {
            recent = service.getRecent(hashedName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        recent.forEach(s -> System.out.println(" " + s));
    }

    public void showCart(List<String> cart) {
        System.out.println("[Client] " + name + " asks service the list in the cart");
        cart.forEach(s -> System.out.println(" " + s));
    }

    public void possiblyBuy(String title) {
        List<String> cart = new ArrayList<>();
        System.out.println("[Client] " + name + " asks service to insert in cart");
        try {
            cart = service.inCart(title, hashedName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("[Client] " + name + " " + cart.size() + " book(s), total " + getTotal());
        showCart(cart);
    }

    private double getTotal() {
        List<Double> cartPrice = new ArrayList<>();
        try {
            service.getPriceCart(hashedName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return cartPrice.stream().reduce(0d, Double::sum);
    }

    public void checkout() {
        System.out.println(
                "[Client] " + name + " asks service to buy book(s) for " + getTotal());
        try {
            service.checkout(hashedName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
