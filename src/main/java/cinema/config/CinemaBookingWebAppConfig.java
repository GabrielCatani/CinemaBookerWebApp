package cinema.config;

import cinema.repositories.UserRepoImpl;
import cinema.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CinemaBookingWebAppConfig {

    @Bean
    public UserRepoImpl userRepo(DataSource dataSource) {
        return new UserRepoImpl(dataSource);
    }

    @Bean
    public UserServiceImpl userService(UserRepoImpl userRepo) {
        return new UserServiceImpl(userRepo);
    }

}
