package nekogochan.petclinic.controller;

import nekogochan.petclinic.entity.Vet;
import nekogochan.petclinic.testutil.TestDbFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.stream.Collectors;

import static nekogochan.petclinic.entity.VetTestBuilder.aVet;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureDataJpa
@Import(TestDbFacade.Config.class)
class VetControllerTest {

    @Autowired
    VetController vetController;
    @Autowired
    private TestDbFacade db;

    @AfterEach
    void afterEach() {
        db.cleanTables(Vet.class);
    }

    @Test
    void getAll() {
        var vet1 = aVet().withName("vet 1");
        var vet2 = aVet().withName("vet 2");
        db.saveAll(vet1, vet2);

        var result = vetController.getAll();
        var resultNames = result.stream().map(Vet::getName).collect(Collectors.toList());

        assertTrue(resultNames.contains("vet 1"));
        assertTrue(resultNames.contains("vet 2"));
    }
}