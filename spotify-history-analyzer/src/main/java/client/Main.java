package client;

import java.io.File; 
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

import com.google.gson.Gson;

import common.StreamRecord;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Inizio il test di Gson...");

        // Proviamo a creare l'oggetto Gson. Se la libreria manca, qui esploderà.

        Gson gson = new Gson();
        System.out.println("2. Oggetto Gson creato con successo! La libreria è presente.");

        // Test di lettura file
        System.out.println("Di quale hanno vuoi vedere quanti ascolti hai?");
        int anno = input.nextInt();
    
        String filePath = "Spotify Extended Streaming History/Streaming_History_Audio_%d-2020_0.json".formatted(anno);
        
        File f = new File(filePath);
        if (f.exists()) {
             System.out.println("3. File JSON trovato: " + f.getAbsolutePath());
             try (Reader reader = new FileReader(filePath)) {
                 StreamRecord[] records = gson.fromJson(reader, StreamRecord[].class);
                 System.out.println("4. LETTURA RIUSCITA! Trovati " + records.length + " record.");
                 if(records.length > 0) {
                     System.out.println(" Primo brano: " + records[0].toString());
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
        } else {
             System.out.println("ATTENZIONE: Non trovo il file JSON, ma se vedi il punto 2, Gson funziona comunque!");
        }
    }
}