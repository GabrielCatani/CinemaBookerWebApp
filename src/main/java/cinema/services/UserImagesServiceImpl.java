package cinema.services;

import cinema.models.User;
import cinema.models.UserImgInfo;
import cinema.repositories.UserImgRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImagesServiceImpl implements UserImagesService{

    private UserImgRepoImpl usrImgRepo;

    @Autowired
    public UserImagesServiceImpl(UserImgRepoImpl usrImgRepo) {
        this.usrImgRepo = usrImgRepo;
    }

    @Override
    public List<UserImgInfo> getAllUserImages(User user) {
        try {
            List<UserImgInfo> imgList = this.usrImgRepo.findAllByUser(user);
            return new ArrayList<UserImgInfo>(imgList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerUserImagePath(UserImgInfo usrImg) {
        this.usrImgRepo.save(usrImg);
    }
}
