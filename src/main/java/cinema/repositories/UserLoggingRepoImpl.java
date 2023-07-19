package cinema.repositories;

import cinema.models.UserLoggingInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserLoggingRepoImpl implements UserLoggingRepo{

    private DataSource dataSource;
    private String loggingTable;
    private String userTable;

    public UserLoggingRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.loggingTable = "cinema_schema.users_logging";
        this.userTable = "cinema_schema.users";
    }

    @Override
    public Optional findById(Long id) {
        String sql = "SELECT * FROM " + this.loggingTable + " WHERE id = ?";
        JdbcTemplate template = new JdbcTemplate(dataSource);

        RowMapper<UserLoggingInfo> rowMapper = (rs, rowNum) -> {
            UserLoggingInfo usrInfo = new UserLoggingInfo();

            usrInfo.setId(rs.getLong("id"));
            usrInfo.setUserId(rs.getLong("userId"));
            usrInfo.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
            usrInfo.setIP(rs.getString("ip"));

            return usrInfo;
        };

        return Optional.of(template.queryForObject(sql, rowMapper, id));
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
