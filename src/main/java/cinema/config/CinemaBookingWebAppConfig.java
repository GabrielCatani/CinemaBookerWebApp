package cinema.config;

import cinema.repositories.UserRepoImpl;
import cinema.services.UserServiceImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:../application.properties")
public class CinemaBookingWebAppConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String usr;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.name}")
    private String driverName;

    @Bean
    public HikariDataSource hikariDao() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(this.url);
        hikariDataSource.setUsername(this.usr);
        hikariDataSource.setPassword(this.password);
        hikariDataSource.setDriverClassName(this.driverName);

        return hikariDataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepoImpl userRepo(DataSource dataSource) {
        return new UserRepoImpl(dataSource);
    }

    @Bean
    public UserServiceImpl userService(UserRepoImpl userRepo) {
        return new UserServiceImpl(userRepo);
    }

}
