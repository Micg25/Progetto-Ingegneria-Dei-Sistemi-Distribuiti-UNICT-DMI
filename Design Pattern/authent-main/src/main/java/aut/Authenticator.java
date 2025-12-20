package aut;

import com.lambdaworks.crypto.SCryptUtil;

public class Authenticator {
    private final Audit log;

    public Authenticator(Audit log) {
        this.log = log;
    }

    public String checkCredentials(String user, String pwd) {
        log.insertLog(user, "richiesta autenticazione con credenziali");
        String authenticationInfo = UserTable.instance().query(user);
        if (null == authenticationInfo)
            return null;
        if (SCryptUtil.check(pwd, authenticationInfo)) {
            Token token = TokenGenerator.tokenBuild(user);
            TokenStore.instance().store(token);
            return token.payload();
        }
        return null;
    }

    public boolean checkHardware(String user, String key) {
        log.insertLog(user, "richiesta autenticazione con hardware");
        return false;
    }

    public boolean checkBio(String user, String key) {
        log.insertLog(user, "richiesta autenticazione con biometria");
        return false;
    }
}
