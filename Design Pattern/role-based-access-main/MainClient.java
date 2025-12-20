/**
 * MainClient is a client class that interacts with service PageController that
 * runs on a server-side. Each request sent to the server will carry the user id
 * and the request id. The request id is then processed on the server side to
 * check whether the user has permission to access the requested resource.
 * Permissions are given to Roles and users are associated to Roles according
 * to design pattern Role Based Access Control.
 */

public class MainClient {

    public static void main(String[] args) {
        PageController p = new PageController();

        // loginUser(); // perform or check login

        System.out.println();
        p.requestOp("Bob", "sendAlert");
        p.requestOp("Bob", "readRefugee");
        p.requestOp("Alice", "sendAlert");
        p.requestOp("Alice", "readRefugee");
    }
}
