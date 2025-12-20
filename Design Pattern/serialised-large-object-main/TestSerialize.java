import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Objects ContainerFinder, Container and Box are a graph. The whole graph is
 * saved when serialising the ContainerFinder instance.
 */
public class TestSerialize {
	private FileOutputStream fos;
	private ObjectOutputStream oos;

	public static void main(String args[]) throws Exception {
		TestSerialize t = new TestSerialize();
		ContainerFinder inMemory = new ContainerFinder();
		System.out.println("Initial values and references");
		t.printValues(inMemory);

		// change values in graph m
		t.setValues(inMemory, 200, 201, 202);

		System.out.println("Changed values in object graph");
		// save graph m
		t.serialize(inMemory);
		System.out.println("** Saved object graph as a Serialized LOB");
		// change values in graph m

		t.setValues(inMemory, 100, 101, 102);

		System.out.println("Changed values in object graph in memory");
		// read graph
		ContainerFinder fromDisk = t.deserialize();

		System.out.println("** After Deserialization, saved values and references");
		t.printValues(fromDisk);
		System.out.println("Values and references in memory");
		t.printValues(inMemory);
	}

	private void serialize(ContainerFinder cf) throws Exception {
		fos = new FileOutputStream("prova13nov.ser");
		oos = new ObjectOutputStream(fos);
		// Serialize object
		oos.writeObject(cf);
		fos.close();
		oos.close();
	}

	private ContainerFinder deserialize() throws Exception {
		FileInputStream fis = new FileInputStream("prova13nov.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		// Deserialize object
		ContainerFinder serobject = (ContainerFinder) ois.readObject();
		fis.close();
		ois.close();
		return serobject;
	}

	private void printValues(ContainerFinder c) {
		System.out.println("References: " + c + " " + c.getContainer() + " ");
		if (c.getContainer().getBox() != null) {
			System.out.println("box: " + c.getContainer().getBox());
			System.out.println("Box width is " + c.getContainer().getBox().getWidth());
			System.out.println("Box height is " + c.getContainer().getBox().getHeight());
		} else
			System.out.println("box is a null field");
		System.out.println("container: " + c.getContainer().get());
	}

	private void setValues(ContainerFinder c, int v1, int v2, int v3) {
		c.getContainer().setWidth(v1);
		c.getContainer().getBox().setHeight(v2);
		c.getContainer().getBox().setWidth(v3);
	}
}
