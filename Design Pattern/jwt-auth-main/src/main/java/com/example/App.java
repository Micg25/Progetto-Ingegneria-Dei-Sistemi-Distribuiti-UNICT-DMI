package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
    private static final String secret = "my-super-secret-key";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println("Generating JWT...");
        String token = getToken();
        System.out.println("Generated Token: " + token);

        System.out.println("Verifying JWT...");
        verifyToken(token);
        System.out.println("Token verified successfully!");

    }

    public static String getToken() {
        // Secret key for HMAC signing (in real apps, keep it safe!)

        // Create HMAC256 algorithm instance
        Algorithm algorithm = Algorithm.HMAC256(secret);

        // Generate a token with a custom claim
        String token = JWT.create()
                .withIssuer("auth0-demo") // optional standard claim
                .withSubject("user123") // optional standard claim
                .withClaim("role", "admin") // custom claim
                .withIssuedAt(new Date()) // issue time
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600_000)) // 1 hour expiry
                .sign(algorithm);

        return token;
    }

    public static void verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0-demo")
                .build();

        DecodedJWT decoded = verifier.verify(token);

        System.out.println("Subject: " + decoded.getSubject());
        System.out.println("Role claim: " + decoded.getClaim("role").asString());
    }
}
