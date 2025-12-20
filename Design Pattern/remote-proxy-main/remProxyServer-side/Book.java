import java.util.ArrayList;
import java.util.List;

/** Book has Role RealSubject for design pattern Proxy */
public class Book implements Volume {
	private int current = 0;
	private List<String> content = new ArrayList<>();

	public Book() {
		content.add("First");
		content.add("Second");
		content.add("Third");
		content.add("Fourth");
		content.add("Fifth");
	}

	@Override
	public String getText() {
		System.out.println("Book: reading text");
		return content.get(current++);
	}

	@Override
	public void append(String s) {
		System.out.println("Book: appending text");
		content.add(s);
	}
}