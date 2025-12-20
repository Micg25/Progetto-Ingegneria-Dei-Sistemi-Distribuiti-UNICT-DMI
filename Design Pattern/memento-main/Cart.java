import java.util.ArrayList;
import java.util.List;

/** Cart is an Originator for design pattern Memento. */
public class Cart {
    private List<Item> items = new ArrayList<>();
    private int discount = 10;

    public Cart(int discount) {
        this.discount = discount;
    }

    public void addToCart(Item item) {
        items.add(item);
    }

    public void printCart() {
        items.forEach(it -> System.out.println(it.nome()));
        float tot = items.stream().map(it -> it.price()).reduce(0.0f, (v, a) -> v + a);
        System.out.println("Total price " + tot);
        float totDiscount = tot * (100 - discount) / 100;
        System.out.println("Total after discount " + totDiscount);
    }

    public StatoCart createMemento() {
        List<Item> l = new ArrayList<>();
        l.addAll(items);
        return new StatoCart(l);
    }

    public void setMemento(StatoCart m) {
        items = m.state();
    }
}