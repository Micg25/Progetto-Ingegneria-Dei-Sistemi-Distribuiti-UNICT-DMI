package aut;

public class SignUp {
    public void registerUser(String user, String pwd) {
        // check if authorised
        UserTable.instance().registerUser(user, pwd);
    }
}
