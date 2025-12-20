package server;

public record User(String userId, String userName, String email, String phoneNumber, String passwordHash,
        boolean isAdmin, String createdAt, String updatedAt) {

}
