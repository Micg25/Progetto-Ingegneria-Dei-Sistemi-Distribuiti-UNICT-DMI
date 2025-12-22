package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import common.SpotifyService;
import common.StreamRecordDTO;

public class Main {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            
            SpotifyService stub = (SpotifyService) registry.lookup("Spotifyservice");
            Scanner input = new Scanner(System.in);
            System.out.println("Insert a year:");
            try{
            if (input.hasNextInt() ) {
                int anno = input.nextInt();
                if(anno >= 2008){
                    List<StreamRecordDTO> result = stub.getSongsByYear(anno);
                    if(result.size() == 0){
                        System.out.println("In the year " + anno + " either you haven't listened to " + result.size() + " songs or there are no datas for this year ");
                    }
                    else
                    System.out.println("In the year " + anno + " you've listened to " + result.size() + " songs!");
                }
                else
                throw new Exception("Invalid year, 2008 minimum");
            } 
            else
            throw new Exception("Invalid Input, instert a year");
        } catch(Exception e) {
                System.out.println(e.getMessage());
                //input.next(); // cleans the input
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}