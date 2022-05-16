package processor;

public class LoginValidate {


    public static boolean validate(String username,String password) {

        if (username.equals("") || username.equals(" ") || password.equals("") || password.equals(" ")) {
            return true;
        }
        if (username == null || password == null) {
            return true;
        }

        return false;
    }

}
