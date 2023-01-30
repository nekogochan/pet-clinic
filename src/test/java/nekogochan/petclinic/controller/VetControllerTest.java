package nekogochan.petclinic.controller;

import nekogochan.petclinic.repository.VetRepository;
import nekogochan.petclinic.service.VetService;
import nekogochan.petclinic.testutil.DbTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DbTest
@ContextConfiguration(classes = {
        VetController.class,
        VetService.class
})
class VetControllerTest {

    @Autowired
    private VetRepository vetRepository;
    @Autowired
    private VetController vetController;

    @Test
    void shit() {
        assertNotNull(vetRepository);
        assertNotNull(vetController);
    }
//    @AfterEach
//    void afterEach() {
//        db.cleanTables(Vet.class);
//    }
//
//    @Test
//    void getAll() {
//        var vet1 = aVet().withName("vet 1");
//        var vet2 = aVet().withName("vet 2");
//        db.saveAll(vet1, vet2);
//
//        var result = vetController.getAll();
//        var resultNames = result.stream().map(Vet::getName).collect(Collectors.toList());
//
//        assertTrue(resultNames.contains("vet 1"));
//        assertTrue(resultNames.contains("vet 2"));
//    }
}