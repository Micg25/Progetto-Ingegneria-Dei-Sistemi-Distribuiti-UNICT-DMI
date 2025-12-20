package server;

import java.rmi.RemoteException;
import java.util.Random;

import shared.Request;
import shared.Response;
import shared.Service;

/** Accepts weather requests. */
public class WeatherProcessor implements Service {

    @Override
    public Response weatherService(Request request) throws RemoteException {
        System.out.println("[Weather] Processing request " + request.requestId() + " for " + request.payload());

        // Simulate a delay before sending the return value
        delay(100 + new Random().nextInt(300));

        return new Response(request.requestId(), processPayload(request.payload()));
    }

    private String processPayload(String payload) {
        // Simulate some processing logic
        System.out.println("[BatchP] Processed: " + payload);
        var result = switch (payload) {
            case "Catania" -> "Sunny";
            case "Milan" -> "Rainy";
            case "Rome" -> "Cloudy";
            case "London" -> "Snowy";
            case "New York" -> "Windy";
            case "Tokyo" -> "Clear";
            case "Bangkok" -> "Hot";
            case "Yokohama" -> "Mild";
            case "Osaka" -> "Cool";
            case "Sydney" -> "Humid";
            case "Honolulu" -> "Breezy";
            case "Taipei" -> "Foggy";
            default -> "Unknown";
        };
        return result;
    }

    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
