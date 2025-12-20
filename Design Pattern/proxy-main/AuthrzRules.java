import java.util.ArrayList;
import java.util.List;

/** Singleton class for managing authorization rules. */
public class AuthrzRules {
    private static final AuthrzRules instance = new AuthrzRules();
    private List<String> readPermits = new ArrayList<>();
    private List<String> writePermits = new ArrayList<>();

    private AuthrzRules() {
        // In this example, read permits are given to anyone for Book.
        readPermits.add("Book");
    }

    public static AuthrzRules getAuthRules() {
        return instance;
    }

    public boolean canRead(Object o) {
        System.out.println("[AuthrzRules] in canRead");
        return readPermits.contains(o.getClass().getSimpleName());
    }

    public boolean canWrite(Object o) {
        System.out.println("[AuthrzRules] in canWrite");
        return writePermits.contains(o.getClass().getSimpleName());
    }
}
