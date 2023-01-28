package nekogochan.petclinic.service;

import nekogochan.petclinic.core.crud.service.CrudService;
import nekogochan.petclinic.core.crud.service.CrudService.WithGetAll;
import nekogochan.petclinic.core.crud.service.CrudService.WithGetOne;
import nekogochan.petclinic.core.crud.service.CrudService.WithSave;
import nekogochan.petclinic.entity.Vet;
import nekogochan.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;

@Service
public class VetService extends CrudService<Vet, Long> implements
        WithGetAll<Vet, Long>,
        WithSave<Vet, Long>,
        WithGetOne<Vet, Long> {
    public VetService(VetRepository repository) {
        super(repository);
    }
}
