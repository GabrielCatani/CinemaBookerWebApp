package cinema.services;

import cinema.models.User;
import cinema.models.UserLoggingInfo;

import java.util.List;

public interface UserLoggingService {

    List<UserLoggingInfo> getAllUserLoggings(User user);

    void registerUserLogging(User user, String IP);
}
