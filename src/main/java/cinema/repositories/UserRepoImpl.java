package cinema.repositories;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepoImpl implements UserRepo{

    private DataSource dataSource;

    public UserRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
