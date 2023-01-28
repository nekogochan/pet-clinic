package nekogochan.petclinic.core.crud.controller;

import nekogochan.petclinic.core.crud.service.CrudService;
import nekogochan.petclinic.core.entity.BaseEntity;

public interface HasCrudService<E extends BaseEntity<ID>, ID> {
    CrudService<E, ID> crudService();
}
