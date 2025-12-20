package client;

import shared.Request;
import shared.Response;
import shared.Service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * A client that sends requests for weather information and handles responses
 * asynchronously.
 */
public class Client {
    private List<CompletableFuture<Response>> results = new ArrayList<>();
    private static int req = 0;
    private final Service service;

    public Client() {
        service = findService();
        if (service == null)
            System.out.println("Could not find service.");
    }

    /**
     * Using async get a thread that will call the weather service. The thread
     * executing the RMI call and the return value are handled in a
     * CompletableFuture that will be completed when the response arrives.
     */
    public void fireRequest(String city) {
        var request = new Request(Integer.toString(req++), city);
        var response = CompletableFuture.supplyAsync(() -> sendRequest(request));

        response.thenAccept(r -> System.out
                .println("[Client] Weather in " + city + ": " + r.result() + ". (Request ID: " + r.requestId() + ")"));
        results.add(response);

        System.out.println("[Client] Requested weather for city: " + city);
    }

    private Response sendRequest(Request request) {
        try {
            return service.weatherService(request);
        } catch (RemoteException e) {
            return null;
        }
    }

    public void pickQuickerResponse() {
        CompletableFuture.anyOf(results.toArray(new CompletableFuture[0]))
                .thenAccept(r -> {
                    Response res = (Response) r;
                    System.out.println("[Client] Quicker response received: " + res.result() + " (Request ID: "
                            + res.requestId() + ")");
                });
    }

    /** Locate the remote service in the RMI registry. */
    private Service findService() {
        Service service = null;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            service = (Service) registry.lookup("WeatherService");
            System.out.println("Service found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }
}
