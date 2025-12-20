package serverpart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** Read and write objects to disk (i.e. serialize objects) */
public class DiskSer {

    public void write(String id, Status status) {
        System.out.println("[DiskSer] write " + id);
        try {
            FileOutputStream fos = new FileOutputStream(id.concat(".ser"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Serialize object
            oos.writeObject(status);
            fos.close();
            oos.close();
            System.out.println("[DiskSer] written OK");
        } catch (FileNotFoundException e1) {
            System.out.println(" ** not found ** " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println(" ** failed ** " + e2.getMessage());
        }
    }

    public Status read(String id) {
        System.out.println("[DiskSer] looking for " + id);
        try {
            FileInputStream fis = new FileInputStream(id.concat(".ser"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Deserialize object
            Status serobject = (Status) ois.readObject();
            fis.close();
            ois.close();
            System.out.println("[DiskSer] read OK");
            return serobject;
        } catch (FileNotFoundException e1) {
            System.out.println("[DiskSer] " + id + " ** not found ** " + e1.getMessage());
            return null;
        } catch (IOException e2) {
            System.out.println(" ** failed ** " + e2.getMessage());
            return null;
        } catch (ClassNotFoundException e3) {
            return null;
        }
    }
}
