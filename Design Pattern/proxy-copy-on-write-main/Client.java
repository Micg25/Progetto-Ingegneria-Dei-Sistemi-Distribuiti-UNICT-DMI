public class Client {
    public static void main(String[] args) {
        Subject firstService = createServiceProxy();
        System.out.println("firstService: " + firstService.request());

        // Obtain second service instance
        Subject secondService = createServiceProxy();
        System.out.println("secondService: " + secondService.request());

        System.out.println("firstService adds a value");
        firstService.update("another value");
        System.out.println("firstService: " + firstService.request());

        System.out.println("secondService: " + secondService.request());
    }

    public static Subject createServiceProxy() {
        return new Proxy();
    }
}
