package cinema.services;

import cinema.models.User;

public interface UserService {
    User signUpUser(User usr);
    User signInUser(User usr);
    User getUserById(long id);

    User getUserByEmail(String email);
}
