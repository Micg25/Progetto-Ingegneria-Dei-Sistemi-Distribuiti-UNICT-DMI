package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import shared.Profile;

/** MessageDB stores processed messages for idempotency */
public class MessageDB {
    private List<ProcessedMessage> updateRequests = new ArrayList<>();
    private Map<ProcessedMessage, Profile> profileRequests = new HashMap<>();

    public Optional<ProcessedMessage> findMessageUpdateProfile(String reqID) {
        return updateRequests.stream().filter(m -> m.messageId().equals(reqID)).findAny();
    }

    public Profile findMessageGetProfile(String reqID) {
        Optional<Entry<ProcessedMessage, Profile>> result = profileRequests.entrySet().stream()
                .filter(e -> e.getKey().messageId().equals(reqID)).findAny();

        if (result.isPresent())
            return result.get().getValue();
        return null;
    }

    public void addMessageUpdate(ProcessedMessage message) {
        updateRequests.add(message);
    }

    public void addMessageProfile(ProcessedMessage message, Profile p) {
        profileRequests.put(message, p);
    }
}
