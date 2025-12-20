import java.io.Serializable;

/**
 * ContainerFinder holds the reference to an instance of Container.
 */
public class ContainerFinder implements Serializable {
	private Container c = new Container();

	public Container getContainer() {
		return c;
	}

	public void setContainer(Container c1) {
		c = c1;
	}
}