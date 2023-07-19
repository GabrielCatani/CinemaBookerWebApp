package repositories;

import cinema.repositories.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

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
                .addScript("sql/schema.sql")
                .addScript("sql/data.sql")
                .build();

        this.userRepo = new UserRepoImpl(this.db);
    }

    @Test
    public void DataBaseConnectionValidation() {
        Assertions.assertNotNull(this.userRepo);
    }
}
