package blog.services;

import blog.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Override
    //This method receives username & password as arguments & as of now it does not really authenticate the user.It always return true
    public boolean authenticate(String username, String password) {
        return true;
    }

    @Override
    public boolean registerNewUser(User user) {
        return false;
    }
}