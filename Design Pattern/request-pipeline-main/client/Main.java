package client;

public class Main {

    public static void main(String[] args) {
        System.out.println("[Main  ] Starting...");
        Client c = new Client();
        c.fireRequest("Bangkok");
        c.fireRequest("Yokohama");
        c.fireRequest("Osaka");
        c.fireRequest("Sydney");
        c.fireRequest("Taipei");
        c.fireRequest("Honolulu");

        System.out.println("[Main  ] All requests sent");

        c.pickQuickerResponse();
        
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
