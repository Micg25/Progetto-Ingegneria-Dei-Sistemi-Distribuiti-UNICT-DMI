package aut;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Generates a unique Token identifying a given user, valid within a time-frame,
 * and signed.
 */
public class TokenGenerator {
    private static final String producer = "MyTokenAuthority";
    private static final String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
    private static final int validity = 2;
    private static final int tokenLength = 30;
    private static KeyPair keyPair;
    private static final TokenGenerator instance = new TokenGenerator();

    private TokenGenerator() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            keyPair = keyGen.generateKeyPair();
            System.out.println("TokenGenerator: keypair generated");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static TokenGenerator getInstance() {
        return instance;
    }

    public Token tokenBuild(final String user) {
        String payload = generatePayload();
        byte[] signat = null;
        try {
            signat = signIt(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Token t = new Token(payload, Instant.now(), Instant.now().plus(validity, ChronoUnit.MINUTES),
                user, producer, signat);
        System.out.println("TokenGenerator, token: " + t.payload());
        return t;
    }

    private String generatePayload() {
        int index;
        String result = "";
        for (int i = 0; i < tokenLength; i++) {
            index = (int) (allChars.length() * Math.random());
            result = result.concat(allChars.substring(index, index + 1));
        }
        return result;
    }

    private byte[] signIt(String payload) throws Exception {
        byte[] text = payload.getBytes();
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(keyPair.getPrivate());
        sig.update(text);
        byte[] signatureBytes = sig.sign();
        return signatureBytes;
    }

    public boolean verifySignature(byte[] s, String payload) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(payload.getBytes());
        return verifier.verify(s);
    }
}
