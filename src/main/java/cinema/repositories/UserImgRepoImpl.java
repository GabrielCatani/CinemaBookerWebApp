package cinema.repositories;

import cinema.models.User;
import cinema.models.UserImgInfo;
import cinema.models.UserLoggingInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class UserImgRepoImpl implements UserImgRepo{

    private DataSource dataSource;
    private String usrImgTable;

    public UserImgRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.usrImgTable = "cinema_schema.user_images";
    }

    @Override
    public Optional findById(Long id) {
        String sql = "SELECT * FROM " + this.usrImgTable + " WHERE id = ?";
        JdbcTemplate template = new JdbcTemplate(dataSource);

        RowMapper<UserImgInfo> rowMapper = (rs, rowNum) -> {
            UserImgInfo usrImg = new UserImgInfo();

            usrImg.setId(rs.getLong("id"));
            usrImg.setUserId(rs.getLong("userId"));
            usrImg.setFileName(rs.getString("fileName"));
            usrImg.setFileSize(rs.getLong("fileSize"));
            usrImg.setMimeType(rs.getString("mime"));

            return usrImg;
        };

        return Optional.of(template.queryForObject(sql, rowMapper, id));
    }

    @Override
    public List findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Object entity) {
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        String sql = "INSERT INTO " + this.usrImgTable + "(userId, fileName, fileSize, mime) " +
                " VALUES (:userId, :fileName, :fileSize, :mime)";

        UserImgInfo usrImg = (UserImgInfo) entity;
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("userId", usrImg.getUserId())
                .addValue("fileName", usrImg.getFileName())
                .addValue("fileSize", usrImg.getFileSize())
                .addValue("mime", usrImg.getMimeType());

        namedTemplate.update(sql, params);
    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Long id) {

    }

    public List<UserImgInfo> findAllByUser(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate((this.dataSource));
        String sql = "SELECT * FROM " + this.usrImgTable + " WHERE userid = ?";

        RowMapper<UserImgInfo> rowMapper = (rs, rowNum) -> {
            UserImgInfo userImages = new UserImgInfo();

            userImages.setId(rs.getLong("id"));
            userImages.setUserId(rs.getLong("userId"));
            userImages.setFileName(rs.getString("fileName"));
            userImages.setFileSize(rs.getLong("fileSize"));
            userImages.setMimeType(rs.getString("mime"));

            return userImages;
        };

        return template.query(sql, rowMapper, user.getId());
    }
}
