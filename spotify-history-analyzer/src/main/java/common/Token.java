package common;
import java.time.Instant;

public record Token(String payload, Instant startTime, Instant endTime, String user, String producer, byte[] signature) {
}