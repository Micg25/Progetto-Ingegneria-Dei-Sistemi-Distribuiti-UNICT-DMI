import java.util.ArrayList;
import java.util.List;

/** Book is a RealSubject implementation of Volume. */
public class Book implements Volume {
    private int current = 0;
    private final List<String> content = new ArrayList<>();

    public Book() {
        // initialize book
        content.add(
                "The Europeans are inclined to regard their own present culture as the only highly developed one and the best.");
        content.add(
                "They tend to believe that other peoples, if they are to make progress in their development, must become just like themselves.");
        content.add("I regard this as a petty conceit.");
    }

    @Override
    public String getText() {
        System.out.println("[Book-RealSubject] in getText");
        if (current < content.size()) {
            return content.get(current++);
        }
        return null;
    }

    @Override
    public void append(String s) {
        System.out.println("[Book-RealSubject] in append");
        content.add(s);
    }
}
