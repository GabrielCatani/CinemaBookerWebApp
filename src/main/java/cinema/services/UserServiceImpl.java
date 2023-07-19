package cinema.services;

import cinema.models.User;
import cinema.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo<User> userRepo;

    @Autowired
    public UserServiceImpl(UserRepo<User> userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean signUpUser(User usr) {
        System.out.println(usr.toString());
        return false;
    }

    @Override
    public boolean signInUser(User usr) {
        return true;
    }

    @Override
    public User getUserById(long id) {
        return (User)this.userRepo.findById(id).get();
    }


}
