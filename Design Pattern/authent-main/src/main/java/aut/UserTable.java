package aut;

import java.util.HashMap;
import java.util.Map;
import com.lambdaworks.crypto.SCryptUtil;

/**
 * UserTable holds AutenticationInfo (pairs user-id and password's hash)
 * for design pattern Authenticator.
 */
public class UserTable {
    private static final UserTable t = new UserTable();
    private Map<String, String> userTable = new HashMap<>();

    private UserTable() {
    }

    public static UserTable instance() {
        return t;
    }

    public String query(String user) {
        if (userTable.containsKey(user))
            return userTable.get(user);
        return null;
    }

    public void registerUser(final String u, final String passwd) {
        if (!u.matches("[a-zA-Z]{1}[a-zA-Z0-9]{2,8}"))
            throw new IllegalArgumentException();
        if (passwd.length() < 8)
            throw new IllegalArgumentException();
        if (userTable.containsKey(u))
            throw new IllegalArgumentException();
        final String hash = SCryptUtil.scrypt(passwd, 32768, 8, 1);
        // System.out.println("hash "+hash);
        userTable.put(u, hash);
    }
}
