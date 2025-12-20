import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;

/** Session holds the roles that are active for a User. */
public class Session {
    // for map roles, the value holds a pair of time stamps, one for the
    // start of the activation of the Role, and one for the expiry
    private final Map<Role, TimePair> roles = new HashMap<>();
    private final User user;

    public Session(User u, Role r, LocalDate start, LocalDate end) {
        user = u;
        roles.put(r, new TimePair(start, end));
    }

    public User getUser() {
        return user;
    }

    public List<Role> getActiveRoles() {
        List<Role> active = roles.entrySet().stream()
                .filter(e -> e.getValue().startTime().isBefore(LocalDate.now())
                        && e.getValue().expiryTime().isAfter(LocalDate.now()))
                .map(Entry::getKey).toList();

        return Collections.unmodifiableList(active);
    }
}

record TimePair(LocalDate startTime, LocalDate expiryTime) {
}
