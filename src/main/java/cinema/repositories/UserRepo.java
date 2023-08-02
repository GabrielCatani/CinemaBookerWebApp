package cinema.repositories;

import java.util.Optional;

public interface UserRepo<T> extends CrudRepo{
    Optional findByEmail(String email);
}
