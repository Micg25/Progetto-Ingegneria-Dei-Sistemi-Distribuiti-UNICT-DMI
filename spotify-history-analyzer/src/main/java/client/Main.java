package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import common.SpotifyService;

public class Main {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            
            SpotifyService stub = (SpotifyService) registry.lookup("Spotifyservice");
            Scanner input = new Scanner(System.in);
            System.out.println("Insert a year:");
            int anno = input.nextInt();

            stub.getSongsByYear(anno);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}