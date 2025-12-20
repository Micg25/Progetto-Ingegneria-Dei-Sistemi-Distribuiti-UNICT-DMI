import java.io.Serializable;

/** Container holds an instance of Box */
public class Container implements Serializable {
	// when field box is transient then the instance of Box will not be serialized
	private Box box = new Box();
	private int width = 50;
	private String nome = "MyBigContainer";

	public Box getBox() {
		return box;
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setBox(Box b) {
		box = b;
	}

	public String get() {
		return nome + " " + String.valueOf(width);
	}
}