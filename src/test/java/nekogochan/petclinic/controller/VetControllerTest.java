package nekogochan.petclinic.controller;

import nekogochan.petclinic.entity.Vet;
import nekogochan.petclinic.service.VetService;
import nekogochan.petclinic.testutil.DbTest;
import nekogochan.petclinic.testutil.TestDbFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Collectors;

import static nekogochan.petclinic.entity.VetTestBuilder.aVet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DbTest
@ContextConfiguration(classes = {
        VetController.class,
        VetService.class
})
class VetControllerTest {

    @Autowired
    private VetController vetController;
    @Autowired
    private TestDbFacade db;

    @AfterEach
    void afterEach() {
        db.cleanTables(Vet.class);
    }

    @Test
    void getAll() {
        var vet2 = aVet().name("vet 2");
        var vet1 = aVet().name("vet 1");
        db.saveAll(vet1, vet2);

        var result = vetController.getAll();
        var resultNames = result.stream().map(Vet::getName).collect(Collectors.toList());

        assertTrue(resultNames.contains("vet 1"));
        assertTrue(resultNames.contains("vet 2"));
    }

    @Test
    void save() {
        var vet = aVet().name("vet");

        var vetId = vetController.save(vet.build());

        var vetFromDb = db.load(Vet.class, vetId);
        assertEquals("vet", vetFromDb.getName());
    }

    @Test
    void getOne() {
        var vetId = db.save(aVet().name("vet")).getId();

        var vet = vetController.getOne(vetId);

        assertEquals("vet", vet.getName());
    }
}