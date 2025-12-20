package domain;

import java.util.List;

public record Track(List<Artist> performers, String title) {
}
