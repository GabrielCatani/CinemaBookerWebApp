package cinema.services;

import cinema.models.User;

public interface UserService {
    boolean signUpUser(User usr);
    boolean signInUser(User usr);
    User getUserById(long id);

}
