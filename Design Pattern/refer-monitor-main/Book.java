import java.util.ArrayList;
import java.util.List;

/**
 * Book plays role ProtectionObject for design pattern Reference Monitor and
 * role RealSubject for design pattern Proxy.
 */
public class Book implements Volume {
    private int current = 0;
    private List<String> content = new ArrayList<>();

    public Book() {
        // From Kitaro Nishida
        // initialise book
        content.add(
                "The Europeans are inclined to regard their own present culture as the only highly developed one and the best.");
        content.add(
                "They tend to believe that other peoples, if they are to make progress in their development, must become just like themselves.");
        content.add("I regard this as a petty conceit.");
    }

    @Override
    public String getText() {
        System.out.println("[Book]: read access");
        if (current < content.size()) {
            return content.get(current++);
        } else {
            System.out.println("[Book ]: End of content reached");
            return null;
        }
    }

    @Override
    public void append(String s) {
        System.out.println("[Book ]: write access");
        content.add(s);
    }
}
