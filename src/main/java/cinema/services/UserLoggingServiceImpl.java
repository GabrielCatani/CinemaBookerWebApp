package cinema.services;

import cinema.models.User;
import cinema.models.UserLoggingInfo;
import cinema.repositories.UserLoggingRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UserLoggingServiceImpl implements UserLoggingService{

    private UserLoggingRepoImpl userLoggingRepo;

    @Autowired
    public UserLoggingServiceImpl(UserLoggingRepoImpl userLoggingRepo) {
        this.userLoggingRepo = userLoggingRepo;
    }

    @Override
    public List<UserLoggingInfo> getAllUserLoggings(User user) {
        try {
            return this.userLoggingRepo.findAllByUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerUserLogging(User user, String IP) {
        UserLoggingInfo userLogInfo = new UserLoggingInfo();

        userLogInfo.setUserId(user.getId());
        userLogInfo.setTimestamp(LocalDateTime.now());
        userLogInfo.setIP(IP);

        this.userLoggingRepo.save(userLogInfo);
    }
}
