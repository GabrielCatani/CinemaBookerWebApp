package cinema.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepoImpl implements UserRepo{

    private DataSource dataSource;

    @Autowired
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
