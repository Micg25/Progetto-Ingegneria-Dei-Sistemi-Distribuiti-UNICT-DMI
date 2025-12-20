import java.rmi.RemoteException;

public class HelloImpl implements Hello {

    public HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello from the RMI Server running on Java 25!";
    }
}
