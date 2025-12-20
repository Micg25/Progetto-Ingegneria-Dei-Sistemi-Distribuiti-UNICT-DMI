package remote;

import java.util.HashMap;
import java.util.Map;
import domain.Artist;
import domain.Album;

public class Registry {
    private static Map<String, Artist> artists = new HashMap<>();
    private static Map<String, Album> albums = new HashMap<>();

    public static Artist findArtistNamed(String artist) {
        return artists.get(artist);
    }

    public static void addArtist(String key, Artist artist) {
        System.out.println("Registry: artist saved " + key);
        artists.put(key, artist);
    }

    public static void addAlbum(String key, Album album) {
        System.out.println("Registry: album saved " + key);
        albums.put(key, album);
    }

    public static Album findAlbum(String album) {
        if (albums.containsKey(album))
            System.out.println("Registry: album found " + album);
        else
            System.out.println("Registry: album not found " + album);
        return albums.get(album);
    }

    public static void updateAlbum(String key, Album album) {
        System.out.println("Registry: album updated " + key);
        albums.replace(key, album);
    }

}
