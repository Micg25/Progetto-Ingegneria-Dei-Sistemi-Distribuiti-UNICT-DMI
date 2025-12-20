/** Protection is a Proxy for class Book implementing Volume. */
public class Protection implements Volume {
    private final Volume book = new Book(); // instance to guarded Book
    private final AuthrzRules authRules = AuthrzRules.getAuthRules();

    @Override
    public String getText() { // security check
        System.out.println("[Proxy-Protection] in getText");
        if (authRules.canRead(book))
            return book.getText();
        System.out.println("[Proxy-Protection] read access has not been granted");
        return null;
    }

    @Override
    public void append(String s) { // security check
        System.out.println("[Proxy-Protection] in append");
        if (authRules.canWrite(book))
            book.append(s);
        else
            System.out.println("[Proxy-Protection] write access has not been granted");
    }
}
