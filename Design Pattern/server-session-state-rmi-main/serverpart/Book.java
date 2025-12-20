package serverpart;

import java.io.Serializable;

/** Book is a product that users can buy. */
public record Book(String author, String title) implements Serializable {
}
