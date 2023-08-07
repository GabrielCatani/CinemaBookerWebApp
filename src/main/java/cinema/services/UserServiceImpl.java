package cinema.services;

import cinema.models.User;
import cinema.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo<User> userRepo;
    private PasswordEncoder psswdEncoder;

    @Autowired
    public UserServiceImpl(UserRepo<User> userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.psswdEncoder = passwordEncoder;
    }

    @Override
    public User signUpUser(User usr) {
        this.userRepo.save(usr);
        return usr;
    }

    @Override
    public User signInUser(User usr) {

        Optional opt = this.userRepo.findByEmail(usr.getEmail());
        if (opt.isPresent()) {
            User savedUser = (User) opt.get();
            if (this.psswdEncoder.matches(usr.getPassword(), savedUser.getPassword())) {
                return savedUser;
            }
        }
        return null;
    }

    @Override
    public User getUserById(long id) {
        return (User)this.userRepo.findById(id).get();
    }

    @Override
    public User getUserByEmail(String email) {
        User usr = null;

        Optional<User> optionalUser = this.userRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            usr = optionalUser.get();
        }
        return usr;
    }


}
