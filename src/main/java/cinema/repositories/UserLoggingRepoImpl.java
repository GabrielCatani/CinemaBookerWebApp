package cinema.repositories;

import cinema.models.User;
import cinema.models.UserLoggingInfo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class UserLoggingRepoImpl implements UserLoggingRepo{

    private DataSource dataSource;
    private String loggingTable;

    public UserLoggingRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.loggingTable = "cinema_schema.users_logging";
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
        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        String sql = "SELECT * FROM " + this.loggingTable;

        RowMapper<UserLoggingInfo> rowMapper = (rs, rowNum) -> {
          UserLoggingInfo userLoggingInfo = new UserLoggingInfo();

          userLoggingInfo.setId(rs.getLong("id"));
          userLoggingInfo.setUserId(rs.getLong("userId"));
          userLoggingInfo.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
          userLoggingInfo.setIP(rs.getString("ip"));

          return userLoggingInfo;
        };

        return template.query(sql, rowMapper);
    }

    @Override
    public void save(Object entity) {
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        String sql = "INSERT INTO " + this.loggingTable + "(userId, timestamp, ip) " +
                " VALUES (:userId, :timestamp, :ip)";

        UserLoggingInfo usrLogging = (UserLoggingInfo) entity;
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("userId", usrLogging.getUserId())
                .addValue("timestamp", new Timestamp(System.currentTimeMillis()))
                .addValue("ip", usrLogging.getIP());

        namedTemplate.update(sql, params);
    }

    @Override
    public void update(Object entity) {
        UserLoggingInfo usrLog = (UserLoggingInfo) entity;

        try {
            findById(usrLog.getId());
        }catch (EmptyResultDataAccessException e) {
            System.err.println(e);
            return;
        }

        String sql = "UPDATE " + this.loggingTable + " SET "
                + "userId = :userId, "
                + "ip = :ip";

        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("userId", usrLog.getUserId())
                .addValue("ip", usrLog.getIP());

        namedTemplate.update(sql, params);
    }

    @Override
    public void delete(Long id) {
        UserLoggingInfo usrLog = null;

        try {
            usrLog = (UserLoggingInfo) findById(id).get();
        } catch (EmptyResultDataAccessException e) {
            System.err.println(e);
            return;
        }

        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        String sql = "DELETE FROM " + this.loggingTable + " WHERE id = ?";

        template.update(sql, new Object[]{id});
    }

    public List<UserLoggingInfo> findAllByUser(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate((this.dataSource));
        String sql = "SELECT * FROM " + this.loggingTable + " WHERE userid = ?";

        RowMapper<UserLoggingInfo> rowMapper = (rs, rowNum) -> {
            UserLoggingInfo userLoggingInfo = new UserLoggingInfo();

            userLoggingInfo.setId(rs.getLong("id"));
            userLoggingInfo.setUserId(rs.getLong("userId"));
            userLoggingInfo.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
            userLoggingInfo.setIP(rs.getString("ip"));

            return userLoggingInfo;
        };

        return template.query(sql, rowMapper, user.getId());
    }
}
