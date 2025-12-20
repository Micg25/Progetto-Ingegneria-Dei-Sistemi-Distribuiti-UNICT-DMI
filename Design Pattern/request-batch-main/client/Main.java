package client;

import shared.Request;

/** A clients that sends requests for weather information. */
public class Main {
    private final static RequestSender sender = new RequestSender(3);

    public static void main(String[] args) {
        System.out.println("Client started");

        sender.addRequest(new Request("req-1", "Catania"));
        sender.addRequest(new Request("req-2", "Milan"));
        sender.addRequest(new Request("req-3", "Tokyo"));
        sender.addRequest(new Request("req-4", "New York"));

        sender.flush();
    }
}
