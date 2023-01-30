package nekogochan.petclinic.testutil;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Import({TestDbFacade.Config.class})
@Transactional(propagation = NOT_SUPPORTED)
@AutoConfigurationPackage(basePackages = {
        "nekogochan.petclinic.repository",
        "nekogochan.petclinic.entity"
})
public @interface DbTest {

    @AliasFor(annotation = DataJpaTest.class, attribute = "properties")
    String[] properties() default {};
}
