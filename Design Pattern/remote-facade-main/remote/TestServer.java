package remote;

import api.AlbumServiceR;
import dto.AlbumDTO;

public class TestServer {
    // local test
    public static void main(String[] args) {
        try {
            AlbumServiceR albmServ = new AlbumServiceImpl();
            AlbumDTO myDto = albmServ.getAlbum("Bare");
            System.out.println("artist: " + myDto.artist());
            System.out.println("title: " + myDto.title());
            System.out.println("tracks: " + myDto.tracks()[0]);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
