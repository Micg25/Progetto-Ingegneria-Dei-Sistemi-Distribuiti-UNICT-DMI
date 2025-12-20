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

    public boolean request(Request req) { // String acc, Object po
        System.out.println("[RefMonitor] ask to find an authorisation");
        boolean granted = rules.exist(req);
        if (!granted) {
            System.out.println("[RefMonitor][Audit]: Access denied for request: " + req.accessRequest() + " on " + req.protectionObject().getClass().getName());
        }
        return granted;
        // RefMonitor trusts the caller (that is the Protection Proxy) to only call the activity when access has been granted.
    }
}
