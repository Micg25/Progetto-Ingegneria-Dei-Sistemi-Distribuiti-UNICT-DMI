package client;

import dto.AlbumDTO;

/**
 * A Client program for the client-side, run after the server AlbServer. The
 * client-side needs to have packages client, api, dto.
 */

public class Client {
    public static void main(String[] args) {
        System.out.println("Client: started... creating proxy");
        AlbumService aServ = new AlbumProxy();
        System.out.println("Client: performing remote request");

        AlbumDTO album = aServ.getAlbum("Bare");

        delay();

        if (album != null) {
            System.out.println("Client: Artist " + album.artist());
            System.out.println("Client: Title " + album.title());
            for (int i = 0; i < album.tracks().length; i++)
                System.out.println("Client: Track " + (i+1) + " " + album.tracks()[i]);
        }
    }


    private static void delay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
