import java.util.List;

/** StatoCart holds a <b>snapshot</b> of a state that has to be saved. */
public record StatoCart(List<Item> state) {

}