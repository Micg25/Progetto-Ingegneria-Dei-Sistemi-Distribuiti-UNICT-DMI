package client;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static RequestSender sender = new RequestSender();
    private static List<Client> clients = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("[Main  ] Starting clients...");
        clients.add(new Client(sender, "Taipei"));
        clients.add(new Client(sender, "Bangkok"));
        clients.add(new Client(sender, "Yokohama"));
        clients.add(new Client(sender, "Osaka"));

        clients.get(0).fireRequest();
        clients.get(1).fireRequest();
        delay(200);

        clients.forEach(c -> c.fireRequest());

        clients.forEach(c -> c.showWeather());

        System.out.println("[Main  ] All requests sent");

        // Wait for all responses before exiting
        delay(3000);
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
