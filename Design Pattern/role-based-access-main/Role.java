import java.util.List;

/**
 * Role represents a role that users can have in an organisation and holds the
 * list of Permission's instances given to each role.
 */
public class Role {
    private final String idRole;
    private final List<Permission> ps;

    public Role(String name, List<Permission> permis) {
        idRole = name;
        ps = permis;
    }

    public boolean hasPermission(String p) {
        return ps.stream().anyMatch(perm -> perm.idPerm().equals(p));
    }

    public String name() {
        return idRole;
    }
}
