package nekogochan.petclinic.controller;

import nekogochan.petclinic.core.crud.controller.CrudController;
import nekogochan.petclinic.core.crud.controller.CrudController.WithGetAll;
import nekogochan.petclinic.core.crud.controller.CrudController.WithGetOne;
import nekogochan.petclinic.core.crud.controller.CrudController.WithSave;
import nekogochan.petclinic.core.crud.service.CrudService;
import nekogochan.petclinic.entity.Vet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vet")
public class VetController extends CrudController<Vet, Long> implements
        WithGetAll<Vet, Long>,
        WithSave<Vet, Long>,
        WithGetOne<Vet, Long> {
    public VetController(CrudService<Vet, Long> service) {
        super(service);
    }
}
