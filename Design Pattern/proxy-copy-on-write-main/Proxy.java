/** Proxy implements copy-on-write for RealSubject. */
public class Proxy implements Subject {
    private static final RealSubject sharedRealSubject = new RealSubject();
    private RealSubject realSubject = sharedRealSubject;

    @Override
    public String request() {
        if (realSubject == sharedRealSubject)
            System.out.println("real subject condiviso");
        else
            System.out.println("real subject esclusivo");
        return realSubject.request();
    }

    @Override
    public void update(final String value) {
        if (realSubject == sharedRealSubject)
            realSubject = new RealSubject();
        realSubject.update(value);
    }
}
