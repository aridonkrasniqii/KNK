
package processor;

// VALIDATION CLASS

import java.util.ArrayList;
import java.util.List;

public class RegisterValidate {



    public  static boolean validate(String name, String username, String email, String password) {

        ArrayList<String> data = new ArrayList<>(List.of(name, username, email, password));

        for (String value : data) {
            if (value == null || value.equals("") || value.equals(" ") || value.length() == 0) {
                return true;
            }

        }
        if (email.indexOf("@") < 0 || email.indexOf(".") < 0) {
            return true;
        }
        if(password.length() < 8 )
        {
            return true;
        }

        return false;
    }


}

