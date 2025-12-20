import java.util.Optional;

/** Protection plays role Proxy for design pattern Proxy */
public class Protection implements Volume {
    private Volume realBook; // instance to guarded RealSubject Book
    private RefMonitor monitor;
    private String subject;

    public Protection(String subject) {
        monitor = RefMonitor.getMonitor();
        realBook = new Book();
        this.subject = subject;
    }

    @Override
    public String getText() {
        System.out.println("[Protec]: check read access");
        Request req = new Request("read", realBook, subject, () -> realBook.getText());
        Optional<?> result = monitor.request(req);
        if (result.isPresent()) {
            System.out.println("[Protec]: Read access granted");
            return (String) result.get();
        }
        System.out.println("[Protec]: Read access denied");
        return null;
    }

    @Override
    public void append(String newLine) {
        System.out.println("[Protec]: check write access");
        Optional<?> result = monitor.request(new Request("write", realBook, subject, () -> {
            realBook.append(newLine);
            return null;
        }));
    }
}
