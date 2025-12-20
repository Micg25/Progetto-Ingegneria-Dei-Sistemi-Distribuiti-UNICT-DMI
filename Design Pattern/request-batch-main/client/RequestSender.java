package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import shared.Service;
import shared.Request;
import shared.Response;

/** Gathers requests and sends them in batches to optimize network usage. */
public class RequestSender {
    private final Service service;
    private final int batchSize;
    private final List<Request> buffer = new ArrayList<>();

    public RequestSender(int batchSize) {
        this.batchSize = batchSize;
        service = findService();
        if (service == null)
            System.out.println("Could not find service.");
    }

    /**
     * Accumulate incoming requests in the buffer, then send a batch of requests to
     * the server when the buffer is full.
     */
    public void addRequest(Request request) {
        buffer.add(request);
        System.out.println("Received request: " + request.requestId());
        if (buffer.size() >= batchSize)
            flush();
    }

    /** Send accumulated requests in the buffer as a batch to the server. */
    public void flush() {
        if (buffer.isEmpty())
            return;
        if (service == null)
            return;
        try {
            List<Response> responses = service.weatherService(buffer);
            printResults(responses);
        } catch (RemoteException e) {
        }
        buffer.clear();
    }

    /** Print responses matching requests. */
    private void printResults(List<Response> responses) {
        responses.forEach(r -> buffer.stream()
                .filter(req -> req.requestId().equals(r.requestId()))
                .findFirst()
                .ifPresent(req -> System.out.println(req.payload() + ": " + r.result())));
    }

    /** Locate the remote service in the RMI registry. */
    private static Service findService() {
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
