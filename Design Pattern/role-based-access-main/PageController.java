/**
 * PageController represents the starting point for interacting with a remote
 * service and is responsible for checking permissions.
 */
public class PageController {
    private Reader db = new Reader();

    public PageController() {
        db.readFile();
    }

    /**
     * parameter request holds the description of the permission needed and
     * correspond to a specific request to a protected object.
     */
    public void requestOp(String userName, String request) {
        // Given a request from a user userName
        // When userName is found in the Registry and has an active Session
        // Then Permission perm can be checked against those in the Role

        db.loadUser(userName);
        User user = Registry.getUser(userName);
        Session session = Registry.getSession(user);

        if (user == null || session == null) {
            System.out.println("[PC] User not found: " + userName);
            return;
        }
        System.out.print("[PC] User found: " + user.name() + "  ");

        boolean hasPerm = session.getActiveRoles()
                .stream()
                .peek(r -> System.out.print("[PC] Role: " + r.name() + " "))
                .anyMatch(r -> r.hasPermission(request));
        if (hasPerm) {
            System.out.println("[PC] Operation " + request + " permitted");
            // perform requested operation here
        } else
            System.out.println("[PC] Operation " + request + " *not* permitted");
    }
}