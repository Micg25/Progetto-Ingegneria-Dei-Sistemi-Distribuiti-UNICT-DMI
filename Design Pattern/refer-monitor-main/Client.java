/**
 * A class performing requests, plays role Subject for design pattern Reference
 * Monitor.
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("[Client]: create an instance of Protection for Book");
        Volume volume = getVolume();
        System.out.println("[Client]: call getText on Protection for Book");
        String text = volume.getText();
        if (text != null)
            System.out.println(text);
        else
            System.out.println("[Client]: Access denied or end of content.");
        System.out.println("[Client]: call append on Protection for Book");
        volume.append("Hello world.");
    }

    private static Volume getVolume() {
        return new Protection("Smith");
    }
}