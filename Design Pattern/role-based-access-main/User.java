import java.util.List;

/**
 * User represents a user, holds a list of Role's instances, could have further
 * attributes.
 */
public record User(String name, List<Role> roles) {
}
