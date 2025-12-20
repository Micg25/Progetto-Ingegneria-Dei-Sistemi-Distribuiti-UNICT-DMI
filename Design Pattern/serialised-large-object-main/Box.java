import java.io.Serializable;

public class Box implements Serializable {
	private int width = 10;
	private int height = 20;

	public int getWidth() {
		return width;
	}

	public void setWidth(int w) {
		width = w;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int h) {
		height = h;
	}
}