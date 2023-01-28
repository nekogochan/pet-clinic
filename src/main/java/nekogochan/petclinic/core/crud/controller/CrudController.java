package nekogochan.petclinic.core.crud.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import nekogochan.petclinic.core.crud.beanpostprocessor.CrudDependsOn;
import nekogochan.petclinic.core.crud.service.CrudService;
import nekogochan.petclinic.core.entity.BaseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
public abstract class CrudController<E extends BaseEntity<ID>, ID> implements HasCrudService<E, ID> {

    private final CrudService<E, ID> service;

    @Override
    public CrudService<E, ID> crudService() {
        return service;
    }

    @CrudDependsOn(CrudService.WithGetAll.class)
    public interface WithGetAll<E extends BaseEntity<ID>, ID> extends HasCrudService<E, ID> {
        @GetMapping("/all")
        default List<E> getAll() {
            return ((CrudService.WithGetAll<E, ID>) crudService()).getAll();
        }
    }

    @CrudDependsOn(CrudService.WithSave.class)
    public interface WithSave<E extends BaseEntity<ID>, ID> extends HasCrudService<E, ID> {
        @PostMapping("/save")
        default ID save(E entity) {
            return ((CrudService.WithSave<E, ID>) crudService()).save(entity).getId();
        }
    }

    @CrudDependsOn(CrudService.WithGetOne.class)
    public interface WithGetOne<E extends BaseEntity<ID>, ID> extends HasCrudService<E, ID> {
        @GetMapping("/{id}")
        default E getOne(@PathParam("id") ID id) {
            return ((CrudService.WithGetOne<E, ID>) crudService()).getOne(id)
                    .orElseThrow();
        }
    }
}
