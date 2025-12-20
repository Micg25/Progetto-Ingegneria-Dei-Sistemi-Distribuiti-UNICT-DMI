package server;

import java.time.Instant;

public record ProcessedMessage(String messageId, Instant processedAt) {
}
