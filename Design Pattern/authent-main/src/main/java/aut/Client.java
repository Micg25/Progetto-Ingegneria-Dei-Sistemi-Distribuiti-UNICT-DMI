package aut;

public class Client {
    private static final String user = "jean";
    private static final String pwd = "passw123123";

    public static void main(String[] args) {
        ServerGateway.signUpRequest(user, pwd);

        String token = ServerGateway.loginRequest(user, pwd);
        System.out.println("[Client] token: " + token);
        String result1 = ServerGateway.request("today", token);
        System.out.println("[Client] result 1: " + result1);
        String result2 = ServerGateway.request("weather", token);
        System.out.println("[Client] result 2: " + result2);

        String result3 = ServerGateway.request("finance", token);
        System.out.println("[Client] result 3: " + result3);

       // ServerGateway.dump();

       // System.out.println();
        highRateRequests(token);
       // ServerGateway.dump();
    }

    private static void highRateRequests(String token) {
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
            String result = ServerGateway.request("weather", token);
            System.out.println("[Client]: " + result);
        }
    }

}
