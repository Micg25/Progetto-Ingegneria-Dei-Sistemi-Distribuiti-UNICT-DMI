import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/** Reads from a configuration file and organise data into maps and lists. */
public class Reader {
    private static final int MAXPERM = 10;
    private static final int MAXROLE = 10;
    private static final int MAXUSER = 10;
    private static final String propFileName = "db.prop";
    private Properties prop = new Properties();
    private Map<String, List<Permission>> roles = new HashMap<>();
    private List<Permission> perms = new ArrayList<>();
    private List<String> users = new ArrayList<>();

    public void readFile() {
        try {
            prop.load(new FileInputStream(propFileName));
            readPerms();
            readRoles();
            readUsers();
        } catch (IOException e) {
            System.err.println("Error reading properties file");
        }
    }

    private String[] getPropValues(String prefix, int i, String ext2) {
        final String ext1 = ".name";
        String[] result = new String[2];
        result[0] = prop.getProperty(prefix + i + ext1);
        result[1] = prop.getProperty(prefix + i + ext2);
        return result;
    }

    private void readPerms() {
        for (int i = 1; i < MAXPERM; i++) {
            String[] permId = getPropValues("perm", i, ".obj");
            if (permId[0] != null && permId[1] != null)
                perms.add(new Permission(permId[0], permId[1]));
        }
    }

    private void readRoles() {
        for (int i = 1; i < MAXROLE; i++) {
            String[] roleNamePerm = getPropValues("role", i, ".perms");
            if (roleNamePerm[0] != null && roleNamePerm[1] != null) {
                String[] indexes = roleNamePerm[1].split(",");
                List<Permission> partPerm = new ArrayList<>();
                for (int j = 0; j < indexes.length; j++) {
                    int l = Integer.valueOf(indexes[j]) - 1;
                    partPerm.add(perms.get(l));
                }
                roles.put(roleNamePerm[0], partPerm);
            }
        }
    }

    private void readUsers() {
        for (int i = 1; i < MAXUSER; i++) {
            String[] user = getPropValues("user", i, ".role");
            if (user[0] != null && user[1] != null)
                users.add(user[0]);
        }
    }

    public boolean loadUser(String name) {
        int i = users.indexOf(name) + 1;
        if (i <= 0)
            return false;
        String userRole = prop.getProperty("user" + i + ".role");
        if (userRole == null)
            return false;
        String[] userRoles = userRole.split(",");
        List<Role> partRoles = new ArrayList<>();
        for (int j = 0; j < userRoles.length; j++)
            partRoles.add(new Role(userRole, roles.get(userRoles[j])));
        User user = new User(name, partRoles);
        Registry.saveUser(name, user);
        loadSession(i, partRoles, user);
        return true;
    }

    private void loadSession(int i, List<Role> partRoles, User u) {
        String sessionStart = prop.getProperty("user" + i + ".session.start");
        String sessionEnd = prop.getProperty("user" + i + ".session.end");
        Session s = new Session(u, partRoles.get(0), LocalDate.parse(sessionStart), LocalDate.parse(sessionEnd));
        Registry.saveSession(u, s);
    }
}
