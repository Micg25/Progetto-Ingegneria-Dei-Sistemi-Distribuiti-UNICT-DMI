package aut;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;

class AppTest {
    private static Authenticator auth;

    @Test
    void knownUser() {
        assertTrue(auth.checkCredentials("jack", "password123"));
    }

    @Test
    void wrongPassword() {
        assertFalse(auth.checkCredentials("jack", "12345"));
    }

    @Test
    void unknownUser() {
        assertFalse(auth.checkCredentials("joe", "j0z1p8"));
    }

    @Test
    void requestDay() {
        ServerGateway.signUpRequest("jack", "password123");
        String token = ServerGateway.loginRequest("jack", "password123");
        assertTrue(ServerGateway.request("today", token).equals(LocalDate.now().getDayOfWeek().name()));
    }

    @BeforeAll
    static void init() {
        UserTable users = new UserTable();
        users.registerUser("jack", "password123");
        auth = new Authenticator(users, new Audit());
    }
}
