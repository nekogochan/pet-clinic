package nekogochan.petclinic.core.crud.service;

import nekogochan.petclinic.core.annotation.ReadOnlyTransactional;
import nekogochan.petclinic.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HasJpaRepository<E extends BaseEntity<ID>, ID> {
    JpaRepository<E, ID> jpaRepo();
}
