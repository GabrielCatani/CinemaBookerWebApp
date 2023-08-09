package cinema.services;

import cinema.models.User;
import cinema.models.UserImgInfo;

import java.util.List;

public interface UserImagesService {

    List<UserImgInfo> getAllUserImages(User user);

    void registerUserImagePath(UserImgInfo usrImg);
}
