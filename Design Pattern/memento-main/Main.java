public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart(15);
        Storage caretaker = new Storage();
        
        cart.addToCart(new Item("Notebook A4", 2));
        cart.addToCart(new Item("Notebook A5", 2));
        cart.addToCart(new Item("Pencil HB", 1.5f));
        cart.addToCart(new Item("Pencil 3B", 1.8f));

        System.out.println("[Main] Saving current state...");
        caretaker.add(cart.createMemento());
        // cart.printCart();

        cart.addToCart(new Item("Color set", 6.5f));
        cart.printCart();

        System.out.println("[Main] Restoring previous state...");
        // go back to a previous state
        cart.setMemento(caretaker.get());
        cart.printCart();
    }
}
