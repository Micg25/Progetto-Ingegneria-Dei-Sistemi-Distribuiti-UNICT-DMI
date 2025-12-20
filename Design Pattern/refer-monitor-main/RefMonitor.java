import java.util.Optional;

/**
 * RefMonitor plays role ReferenceMonitor.
 */
public class RefMonitor {
    private static final RefMonitor instance = new RefMonitor();
    private final SetOfAuthRules rules = new SetOfAuthRules();

    private RefMonitor() {
        rules.fillAuthRules();
    }

    public static RefMonitor getMonitor() {
        return instance;
    }

    public Optional<?> request(Request req) {
        System.out.println("[RefMon]: ask to find an authorisation");
        boolean granted = rules.exist(req);
        if (!granted) {
            System.out.println("[RefMon]: Access denied for request: " + req.accessRequest() + " on "
                    + req.protectionObject().getClass().getName());
        }
        else {
            try {
                return Optional.ofNullable(req.action().call());
            } catch (Exception e) {
                System.out.println("[RefMon]: Exception during action: " + e.getMessage());
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
