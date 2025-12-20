package shared;

import java.io.Serializable;

public record Request(String requestId, String payload) implements Serializable {
}
