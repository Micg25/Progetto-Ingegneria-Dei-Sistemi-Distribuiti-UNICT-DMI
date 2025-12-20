import java.util.HashMap;
import java.util.Map;

/**
 * Design pattern Registry. Given a string id for the user, find the
 * corresponding instance. Given the User instance, find the Session instance
 * for that user.
 */
public class Registry {
    private static Map<String, User> users = new HashMap<>();
    private static Map<User, Session> sessions = new HashMap<>();

    public static User getUser(String id) {
        return users.get(id);
    }

    public static Session getSession(User u) {
        return sessions.get(u);
    }

    public static void saveUser(String id, User u) {
        users.put(id, u);
    }

    public static void saveSession(User u, Session s) {
        sessions.put(u, s);
    }
}
