package serverpart;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * Status holds the list of books in the cart, and the list of
 * recently searched books. Serialization is used to passivate the object.
 */
public record Status(List<Book> cart, Set<Book> recent, Instant timestamp) implements Serializable {

}
