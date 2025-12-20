package aut;

import java.time.Instant;

public record Token(String payload, Instant starTime, Instant endTime, 
                    String user, String producer, byte[] signature) {
}
