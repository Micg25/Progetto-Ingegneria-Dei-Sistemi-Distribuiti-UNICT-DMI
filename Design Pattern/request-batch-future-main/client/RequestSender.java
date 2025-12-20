package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import shared.Service;
import shared.Request;
import shared.Response;

/** Gathers requests and sends them in batches to optimize network usage. */
public class RequestSender {
    private final Service service;
    private final List<Request> buffer = new ArrayList<>();
    private final Map<String, CompletableFuture<Response>> futures = new HashMap<>();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public RequestSender() {
        service = findService();
        if (service == null)
            System.out.println("Could not find service.");
    }

    /**
     * Accumulate incoming requests in the buffer. Set a scheduled activity to flush
     * them after a short delay. Return a CompletableFuture that will be completed
     * when the response arrives.
     */
    public CompletableFuture<Response> addRequest(Request request) {
        System.out.println("[Sender] Received request: " + request.requestId());
        buffer.add(request);
        if (buffer.size() == 1)
            executor.schedule(() -> flush(), 100, TimeUnit.MILLISECONDS);
        var future = new CompletableFuture<Response>();
        futures.put(request.requestId(), future);
        System.out.println("[Sender] Added request to buffer.");
        return future;
    }

    /** Send all accumulated requests as a batch to the server. */
    private void flush() {
        System.out.println("[Sender] Flushing requests...");
        if (buffer.isEmpty())
            return;
        if (service == null)
            return;
        try {
            System.out.println("[Sender] Sending batch of " + buffer.size() + " requests");
            List<Response> responses = service.weatherService(buffer);
            System.out.println("[Sender] Received batch of " + responses.size() + " responses");
            responses.forEach(r -> giveResponse(r));
        } catch (RemoteException e) {
        }
        buffer.clear();
    }

    /** Complete the corresponding future with the received response. */
    private void giveResponse(Response r) {
        var future = futures.get(r.requestId());
        if (future != null) {
            future.complete(r);
            futures.remove(r.requestId());
        }
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
