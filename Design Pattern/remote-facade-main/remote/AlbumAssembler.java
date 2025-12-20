package remote;

import java.util.ArrayList;
import java.util.List;
import domain.Album;
import domain.Artist;
import domain.Track;
import dto.AlbumDTO;

public class AlbumAssembler {
    public AlbumDTO writeDTO(Album album) {
        System.out.println("AlbumAssembler: start building dto");
        List<String> tracks = album.tracks().stream().map(t -> t.title()).toList();
        System.out.println("AlbumAssembler: read tracks from album");
        AlbumDTO result = new AlbumDTO(album.artist().name(),
                album.title(), tracks.toArray(new String[tracks.size()]));
        System.out.println("AlbumAssembler: returning dto");
        return result;
    }

    public void createAlbum(String id, AlbumDTO source) {
        Album album = new Album(findArtist(source.artist()), source.title(), trackList(source));
        System.out.println("AlbumAssembler: new album created");
        Registry.addAlbum(id, album);
    }

    public void updateAlbum(String id, AlbumDTO source) {
        Album prevAlbum = Registry.findAlbum(id);
        if (prevAlbum == null)
            throw new RuntimeException("AlbumAssembler: album " + source.title() + " does not exist");
        Album album = new Album(findArtist(source.artist()), source.title(), trackList(source));
        Registry.updateAlbum(id, album);
    }

    private Artist findArtist(String artistName) {
        Artist artist = Registry.findArtistNamed(artistName);
        if (artist == null)
            throw new RuntimeException("AlbumAssembler: no artist named " + artistName);
        // TODO: create new artist if it does not exist
        return artist;
    }

    private List<Track> trackList(AlbumDTO source) {
        List<Track> tracks = new ArrayList<>();
        for (int i = 0; i < source.tracks().length; i++)
            tracks.add(new Track(List.of(findArtist(source.artist())), source.tracks()[i]));
        return tracks;
    }
}