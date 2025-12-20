package shared;

import java.io.Serializable;

public record Response(String requestId, String result) implements Serializable {
}
