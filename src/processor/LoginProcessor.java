package processor;


import models.User;
import repositories.UserRepository;

public class LoginProcessor {

    public User login(String username, String password) throws Exception {
        User user = UserRepository.find(username);
        if(user == null)
            return user;

        String hashedPassword = SecurityHelper.computeHash(password,user.getSalt());
        if(!user.getPassword().equals(hashedPassword)){
            return null;
        }

        return user;
    }


}
