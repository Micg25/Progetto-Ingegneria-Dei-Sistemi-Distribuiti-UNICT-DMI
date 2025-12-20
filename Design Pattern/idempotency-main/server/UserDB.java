package server;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User findUser(String userId) {
        return users.stream()
                .filter(user -> user.userId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userId().equals(updatedUser.userId())) {
                users.set(i, updatedUser);
                return;
            }
        }
    }
}
