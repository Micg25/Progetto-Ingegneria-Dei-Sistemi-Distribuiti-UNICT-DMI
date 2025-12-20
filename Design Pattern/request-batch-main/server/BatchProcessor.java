package server;

import java.rmi.RemoteException;
import java.util.List;

import shared.Request;
import shared.Response;
import shared.Service;

/** Processes batches of weather requests. */
public class BatchProcessor implements Service {

    @Override
    public List<Response> weatherService(List<Request> requests) throws RemoteException {
        System.out.println("Processing batch with " + requests.size() + " requests");

        return requests.stream()
                .map(r -> new Response(r.requestId(), processPayload(r.payload())))
                .toList();
    }

    private String processPayload(String payload) {
        // Simulate some processing logic
        System.out.println("Processed: " + payload);
        var result = switch (payload) {
            case "Catania" -> "Sunny";
            case "Milan" -> "Rainy";
            case "Rome" -> "Cloudy";
            case "London" -> "Snowy";
            case "New York" -> "Windy";
            case "Tokyo" -> "Clear";
            case "Sydney" -> "Humid";
            case "Honolulu" -> "Breezy";
            default -> "Unknown";
        };
        return result;
    }
}
