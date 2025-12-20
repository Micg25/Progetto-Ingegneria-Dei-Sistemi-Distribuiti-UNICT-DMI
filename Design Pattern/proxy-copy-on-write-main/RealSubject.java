import java.util.ArrayList;
import java.util.List;

public class RealSubject implements Subject {
    private final List<String> data = new ArrayList<>();
    private int currentIndex = 0;

    public RealSubject() {
        data.add("first");
        data.add("second");
        data.add("third");
    }

    @Override
    public String request() {
        if (currentIndex == data.size())
            currentIndex = 0;
        return data.get(currentIndex++);
    }

    @Override
    public void update(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }
        data.add(value);
    }

}
