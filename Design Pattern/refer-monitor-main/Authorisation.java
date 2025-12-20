/**
 * Authorisation represents an access rule for a subject, object, and access type.
 */
public record Authorisation(String subject, String object, String accessType, boolean allowed) {
    /**
     * Checks if the authorisation allows the access.
     */
    public boolean exist() {
        return allowed;
    }
}
