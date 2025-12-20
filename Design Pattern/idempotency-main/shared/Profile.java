package shared;

import java.io.Serializable;

/** Profile is a DTO */
public record Profile(String userId, String userName, String email, String phoneNumber) implements Serializable {

}
