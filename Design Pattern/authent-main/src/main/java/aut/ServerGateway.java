package aut;

import com.google.common.util.concurrent.RateLimiter;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class ServerGateway {
    private static final RateLimiter rateLimiter = RateLimiter.create(3);
    private static final Audit audit = new Audit();
    private static final Authenticator auth = new Authenticator(audit);
    private static final SignUp signUp = new SignUp();
    private static boolean dropRequestMode = false;

    public static String request(String service, String token) {
        if (dropRequestMode && !rateLimiter.tryAcquire(10, TimeUnit.MICROSECONDS))
            return "quick fail";

        rateLimiter.acquire();
        if (!TokenStore.instance().isPresent(token))
            return "invalid token, perform login";
        audit.insertLog(TokenStore.instance().findUser(token), "richiesta " + service);

        // after checking authorisation here

        if (service.equals("weather"))
            return "a sunny day";
        if (service.equals("today"))
            return LocalDate.now().getDayOfWeek().name();

        audit.insertLog(TokenStore.instance().findUser(token), "richiesta sconosciuta");
        return "failed, request unknown";
    }

    public static String loginRequest(String user, String pwd) {
        rateLimiter.acquire();
        String token = auth.checkCredentials(user, pwd);
        if (null != token) {
            audit.insertLog(user, "identificato");
            return token;
        }
        audit.insertLog(user, "sconosciuto");
        return "user unknown";
    }

    public static void signUpRequest(String user, String pwd) {
        rateLimiter.acquire();
        signUp.registerUser(user, pwd);
        audit.insertLog(user, "registrato");
    }

    public static void dump() {
        rateLimiter.acquire();
        audit.dumpLogTable();
    }
}
