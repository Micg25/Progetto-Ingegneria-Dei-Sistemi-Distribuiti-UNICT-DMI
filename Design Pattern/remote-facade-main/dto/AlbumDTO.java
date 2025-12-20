package dto;

import java.io.Serializable;

/**
 * AlbumDTO implements pattern Data Transfer Object (DTO) to have a proper data
 * exchange between the client-side and server-side
 */

public record AlbumDTO(String artist, String title, String[] tracks) implements Serializable {
}
