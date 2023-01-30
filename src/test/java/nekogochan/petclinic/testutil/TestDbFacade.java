package nekogochan.petclinic.testutil;

import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;

@Component
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class TestDbFacade {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveAll(TestBuilder<?>... builders) {
        for (var b : builders) {
            save(b);
        }
    }

    @Configuration
    public static class Config {
        @Bean
        TestDbFacade testDbFacade() {
            return new TestDbFacade();
        }
    }

    public void cleanTables(String... tableNames) {
        transactionTemplate.execute(__ -> {
            JdbcTestUtils.deleteFromTables(jdbcTemplate, tableNames);
            return null;
        });
    }

    public void cleanTables(Class<?>... entityClasses) {
        var tableNames = Arrays.stream(entityClasses)
                .map(c -> c.getAnnotation(Table.class).name())
                .toArray(String[]::new);
        cleanTables(tableNames);
    }

    public <T> T find(Class<T> entityClass, Object id) {
        return transactionTemplate.execute(__ -> testEntityManager.find(entityClass, id));
    }

    public <T> T save(TestBuilder<T> builder) {
        return transactionTemplate.execute(__ -> testEntityManager.persistAndFlush(builder.build()));
    }
}
