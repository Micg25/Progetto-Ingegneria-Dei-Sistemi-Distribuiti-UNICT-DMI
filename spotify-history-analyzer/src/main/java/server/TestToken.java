package server;

import java.time.Instant;

import common.Token;
public class TestToken {
    private static final TokenGenerator tGen = TokenGenerator.getInstance();
    private static final TokenStore tStore = TokenStore.getInstance();
    private Token tokenAl;
    private Token tokenBob;
    private Token tokenChuck;
    public static void main(String[] args) {
        TestToken test = new TestToken();
        test.chuckToken();
        test.initTokens();
        test.testNotExpired();
        test.testNotValid();
        test.printInfo();
    }

    private void chuckToken() {
        tokenChuck = tGen.tokenBuild("Chuck");
        tStore.store(tokenChuck);
        System.out.println("token generato e firmato");
        
        assertTrue(tStore.isValid(tokenChuck.payload()), "token found and not expired for " + tokenChuck.user());
        assertTrue(tStore.isPresent(tokenChuck), "token found for " + tokenChuck.user());
        assertTrue(tStore.isValidSignature(tokenChuck), "token signature ok for " + tokenChuck.user());
    }

    private void initTokens() {
        tokenAl = tGen.tokenBuild("Al");
        tStore.store(tokenAl);
        tokenBob = tGen.tokenBuild("Bob");
        tStore.store(tokenBob);
        assertTrue(tStore.isValid(tokenAl.payload()), "token found and not expired for " + tokenAl.user());
        assertTrue(tStore.isValid(tokenBob.payload()), "token found and not expired for " + tokenBob.user());
    }

    private void testNotExpired() {
        assertTrue(tokenAl.endTime().isAfter(Instant.now()), "Al's token within 2 minutes");
    }

    private void testNotValid() {
        assertTrue(!tStore.isValid("mAXtX2zJs98LtANyuzmUZrYZsCGyQ4"), "unknown token");
    }

    private void printInfo() {
        System.out.println("momento di creazione: " + tokenAl.startTime());
        System.out.println("nome utente: " + tokenAl.user());
        System.out.println("nome produttore: " + tokenAl.producer());
    }

    public void assertTrue(boolean result, String msg) {
        if (result)
            System.out.println("OK: " + msg);
        else
            System.out.println("FAIL: " + msg);
    }
}