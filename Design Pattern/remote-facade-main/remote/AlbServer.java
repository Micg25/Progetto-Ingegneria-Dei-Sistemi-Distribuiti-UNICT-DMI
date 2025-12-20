package remote;

import java.rmi.Naming;
import java.util.List;

import api.AlbumServiceR;
import domain.Album;
import domain.Artist;
import domain.Track;

/**
 * A Server program for the server-side. Firstly, run rmiregistry in the
 * root folder of classes, from the shell. Secondly, run AlbServer and finally
 * run Client. In the server-side needs to be deployed packages api, dto,
 * domain, remote.
 */
public class AlbServer {
    // the name of the service for the RMI registry // 127.0.0.1
    private static final String objectURL = "rmi://localhost:1099/AlbumService";

    public static void main(String[] args) {
        System.out.println("AlbServer: starting server");

        try {
            AlbumServiceR albmServ = new AlbumServiceImpl();
            System.out.println("AlbServer: service instance created");
            // store the service reference in the RMI registry
            Naming.rebind(objectURL, albmServ);
            System.out.println("AlbServer: service instance registered");
        } catch (Exception e) {
            System.err.println(e);
        }
        
        System.out.println("AlbServer: creating album");
        createFirstAlbum();
    }

    public static void createFirstAlbum() {
        Artist annie = new Artist("Annie Lennox");
        Artist dave = new Artist("Dave");
        Registry.addArtist("Annie", annie);
        Registry.addArtist("Dave", dave);
        Track tr1 = new Track(List.of(annie, dave), "A Thousand Beautiful Things");
        Track tr2 = new Track(List.of(annie, dave), "Pavement Cracks");
        Album al = new Album(annie, "Bare", List.of(tr1, tr2));
        Registry.addAlbum("Bare", al);
    }
}
