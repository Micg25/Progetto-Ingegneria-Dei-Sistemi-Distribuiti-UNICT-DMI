package client;

import shared.Request;
import shared.Response;

import java.util.concurrent.CompletableFuture;

/**
 * A client that sends requests for weather information and handles responses
 * asynchronously.
 */
public class Client {
    private String city;
    private CompletableFuture<Response> result;
    private RequestSender sender;
    private static int req = 0;

    /**
     * Initialize the client with a sender and a city.
     */
    public Client(RequestSender sender, String city) {
        this.city = city;
        this.sender = sender;
    }

    /**
     * Fire the request to get weather for the city. The request returns a
     * CompletableFuture that will be completed when the response arrives.
     */
    public void fireRequest() {
        result = sender.addRequest(new Request(Integer.toString(req++), city));
        System.out.println("[Client] Added request for city: " + city);
    }

    /** Display the weather result when it arrives. */
    public void showWeather() {
        System.out.println("[Client] Fetching weather for: " + city);
        result.thenAccept(r -> System.out.println("[Client] Weather in " + city + ": " + r.result()));
    }
}
