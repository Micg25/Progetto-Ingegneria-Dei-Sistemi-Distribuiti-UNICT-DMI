package domain;

import java.util.List;

public record Album(Artist artist, String title, List<Track> tracks) {
}
