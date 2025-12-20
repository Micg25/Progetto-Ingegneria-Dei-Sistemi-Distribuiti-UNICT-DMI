/**
 * Provides the main use cases for the pattern Role Based Access Control. For
 * each User (a Role has been assigned) and then it is checked whether permissions are
 * held or not according to the given role.
 */
public class Test extends MyTestSupport {
    private User alice, bob, joe;

    public static void main(String[] args) {
        Test test = new Test();
        Reader db = new Reader();
        db.readFile();
        db.loadUser("Alice");
        db.loadUser("Bob");
        db.loadUser("Joe");

        test.findFromRegistry();

        test.hasBobSendAlertPerm();
        test.hasJoeReadPerm();
        test.hasAliceNoWritePerm();
        test.hasJoeNoWritePerm();
        test.roleNames();
        test.activeSessionBob();
    }

    private void findFromRegistry() {
        System.out.println("\n[Test] Is each user inside Registry?");
        alice = Registry.getUser("Alice");
        bob = Registry.getUser("Bob");
        joe = Registry.getUser("Joe");

        assertNotNull(alice, "Alice was found in Registry");
        assertNotNull(bob, "Bob was found in Registry");
        assertNotNull(joe, "Joe was found in Registry");
    }

    private void roleNames() {
        System.out.println("\n[Test] Has each user the correct role?");
        String roleNameA = alice.roles().get(0).name();
        assertEquals(roleNameA, "Participant", "role name Participant for Alice");
        String roleNameB = bob.roles().get(0).name();
        assertEquals(roleNameB, "SecurityOfficer", "role name SecurityOfficer for Bob");
        String roleNameJ = joe.roles().get(0).name();
        assertEquals(roleNameJ, "Secretary", "role name Secretary for Joe");
    }

    private void hasJoeReadPerm() {
        System.out.println("\n[Test] Has Joe ReadDisk Permission?");
        boolean hasPerm = joe.roles().stream().anyMatch(r -> r.hasPermission("readDisk"));
        assertTrue(hasPerm, " User: " + joe.name() + ", role: " + joe.roles().get(0).name()
                + ", permission Read Disk");
    }

    private void hasBobSendAlertPerm() {
        System.out.println("\n[Test] Has Bob SendAlert Permission?");
        boolean hasPerm = bob.roles().stream().anyMatch(r -> r.hasPermission("sendAlert"));
        assertTrue(hasPerm, "[Test] User: " + bob.name() + ", role: " + bob.roles().get(0).name()
                + ", permission SendAlert");
    }

    private void hasJoeNoWritePerm() {
        System.out.println("\n[Test] Has Joe No Write Permission?");
        boolean hasPerm = joe.roles().stream().anyMatch(r -> r.hasPermission("write"));
        assertTrue(!hasPerm, "User " + joe.name() + " has no permission for Write Disk");
    }

    private void hasAliceNoWritePerm() {
        System.out.println("\n[Test] Has Alice No Write Permission?");
        boolean hasPerm = alice.roles().stream().anyMatch(r -> r.hasPermission("write"));
        assertTrue(!hasPerm, "User " + alice.name() + " is not allowed operation Write Disk");
    }

    private void activeSessionBob() {
        System.out.println("\n[Test] Is a Session for Bob active?");
        User u = Registry.getUser("Bob");
        Session s = Registry.getSession(u);

        System.out.println(s.getUser().name() + " is a " + s.getActiveRoles().get(0).name() + " in this session");

        boolean hasPerm = s.getActiveRoles().stream().anyMatch(r -> r.hasPermission("sendAlert"));
        assertTrue(hasPerm, "User " + u.name() + " has permission to send Alert in active session");
    }
}
