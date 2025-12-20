/** Protection plays role Proxy for design pattern Proxy */
public class Protection implements Volume {
    private Volume realBook; // instance to guarded Book
    private RefMonitor monitor;
    private String subject;
    
    public Protection(String subject) {
        monitor = RefMonitor.getMonitor();
        realBook = new Book();
        this.subject = subject;
    }

    @Override
    public String getText() {
        // performs access checks before calling the RealSubject
        System.out.println("[Proxy]: check read access");
        // it is asked the reference monitor to check a read access for the realBook
        if (monitor.request(new Request("read", realBook, subject))) {
            System.out.println("[Proxy]: Read access granted");
            return realBook.getText();
        }
        System.out.println("[Proxy]: Read access has not been granted");
        logDeniedAccess("read");
        return null;
    }

    @Override
    public void append(String newLine) {
        System.out.println("[Proxy]: check write access");
        // security check
        if (monitor.request(new Request("write", realBook, subject))) {
            System.out.println("[Proxy]: Write access granted");
            realBook.append(newLine);
        } else {
            System.out.println("[Proxy]: Write access has not been granted");
            logDeniedAccess("write");
        }
    }

    private void logDeniedAccess(String accessType) {
        System.out.println("[Proxy][Audit]: Denied " + accessType + " access to Book.");
    }
}
