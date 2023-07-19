package cinema.repositories;

import cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.sql.JDBCType.BIGINT;

@Repository
public class UserRepoImpl implements UserRepo{

    private DataSource dataSource;
    private String table;

    @Autowired
    public UserRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.table = "cinema_schema.users";
    }

    @Override
    public Optional findById(Long id) {
        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        String sql = "SELECT * FROM " + this.table + " WHERE id = ?";

        RowMapper<User> rowMapper = (rs, rowNum) -> {
            User usr = new User();
            usr.setId(rs.getLong("id"));
            usr.setEmail(rs.getString("email"));
            usr.setFirstName(rs.getString("firstName"));
            usr.setLastName(rs.getString("lastName"));
            usr.setPhoneNumber(rs.getString("phone"));
            usr.setPassword(rs.getString("passwd"));

            return usr;
        };

        return Optional.of(template.queryForObject(sql, rowMapper, id));
    }

    @Override
    public List findAll() throws SQLException {
        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        String sql = "SELECT * FROM " + this.table;

        RowMapper<User> rowMapper = (rs, rowNum) -> {
            User usr = new User();

            usr.setId(rs.getLong("id"));
            usr.setEmail(rs.getString("email"));
            usr.setFirstName(rs.getString("firstName"));
            usr.setLastName(rs.getString("lastName"));
            usr.setPhoneNumber(rs.getString("phone"));
            usr.setPassword(rs.getString("passwd"));
            return usr;
        };

        return template.query(sql, rowMapper);
    }

    @Override
    public void save(Object entity) {
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        String sql = "INSERT INTO " + this.table + " (email, firstName, lastName, phone, passwd)"
                + " VALUES (:email, :firstName, :lastName, :phone, :passwd)";

        User usr = (User)entity;
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("email", usr.getEmail())
                .addValue("firstName", usr.getFirstName())
                .addValue("lastName", usr.getLastName())
                .addValue("phone", usr.getPhoneNumber())
                .addValue("passwd", usr.getPassword());

        namedTemplate.update(sql, params);
    }

    @Override
    public void update(Object entity) {
        User usr = (User)entity;

        try {
            findById(usr.getId());
        }catch (EmptyResultDataAccessException e) {
            System.err.println(e);
            return;
        }

        String sql = "UPDATE " + this.table + "SET "
                + "email = :email, "
                + "firstName = :firstName,"
                + "lastName = :lastName,"
                + "phone = :phone,"
                + "passwd = :passwd";

        NamedParameterJdbcTemplate nameTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("email", usr.getEmail())
                .addValue("firstName", usr.getFirstName())
                .addValue("lastName", usr.getLastName())
                .addValue("phone", usr.getPhoneNumber())
                .addValue("passwd", usr.getPassword());

        nameTemplate.update(sql, params);
    }

    @Override
    public void delete(Long id) {
        User usr = null;
        try {
            usr = (User)findById(id).get();
        }catch (EmptyResultDataAccessException e) {
            System.err.println(e);
            return;
        }

        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        String sql = "DELETE FROM " + this.table + " WHERE id = ?";

        template.update(sql, new Object[]{id});
    }
}
