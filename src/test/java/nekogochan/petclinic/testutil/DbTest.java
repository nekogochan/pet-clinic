package nekogochan.petclinic.testutil;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@Profile("db-test")
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
@Import({TestDbFacade.Config.class})
@Transactional(propagation = NOT_SUPPORTED)
public @interface DbTest {

//    @AliasFor(annotation = DataJpaTest.class, attribute = "properties")
//    String[] properties() default {};
}
