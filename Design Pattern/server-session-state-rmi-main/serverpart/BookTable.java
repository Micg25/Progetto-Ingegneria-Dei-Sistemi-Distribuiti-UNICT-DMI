package serverpart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookTable {
    private final Map<Book, Double> books = new HashMap<>();

    public BookTable() {
        books.put(new Book("E. Gamma, R. Helm, et al", "Design Patterns"), 54d);
        books.put(new Book("E. Freeman, E. Robson", "Head First Design Patterns"), 33d);
        books.put(new Book("O. Zimmermann, M. Stocker, et al", "Patterns for API Design"), 55d);
        books.put(new Book("R. C. Martin", "Clean Architecture"), 34d);
        books.put(new Book("J. J. Geewax", "API Design Patterns"), 60d);
        books.put(new Book("M. Fowler", "Refactoring: Improving the Design of Existing Code"), 54d);
        books.put(new Book("M. Fowler", "Patterns of Enterprise Application Architecture"), 61d);
        books.put(new Book("C. Richardson", "Microservices Patterns"), 41d);
    }

    /**
     * @param title is the whole title of the book
     */
    public Book getBook(String title) {
        return books.keySet().stream().filter(b -> b.title().equals(title)).findAny()
                .orElse(null);
    }

    /**
     * @param key is a portion of the title
     */
    public List<Book> getList(String key) {
        return books.keySet().stream().filter(b -> b.title().indexOf(key) > -1)
                .toList();
    }

    public double getPrice(Book book) {
        return books.get(book);
    }
}
