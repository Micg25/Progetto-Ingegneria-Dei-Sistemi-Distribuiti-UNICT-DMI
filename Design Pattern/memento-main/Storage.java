import java.util.Stack;

/** Storage is a caretaker that manages the saved states of the Cart. */
public class Storage {
    private Stack<StatoCart> mStack = new Stack<>();

    public void add(StatoCart m) {
        System.out.println("[Storage] Saving state...");
        mStack.push(m);
    }

    public StatoCart get() {
        System.out.println("[Storage] Restoring state...");
        return mStack.pop();
    }
}
