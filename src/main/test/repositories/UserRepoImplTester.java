package repositories;

import cinema.repositories.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;


public class UserRepoImplTester {

    private EmbeddedDatabase db;

    private UserRepoImpl userRepo;

    @BeforeEach
    public void init() {
        this.db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        this.userRepo = new UserRepoImpl(this.db);
    }

    @Test
    public void DataBaseConnectionValidation() {
        try {
            Assertions.assertNotNull(this.db.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotNull(this.userRepo);
    }
}
