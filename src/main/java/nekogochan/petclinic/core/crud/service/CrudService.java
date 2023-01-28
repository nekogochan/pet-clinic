package nekogochan.petclinic.core.crud.service;

import lombok.RequiredArgsConstructor;
import nekogochan.petclinic.core.annotation.ReadOnlyTransactional;
import nekogochan.petclinic.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CrudService<E extends BaseEntity<ID>, ID> implements HasJpaRepository<E, ID> {
    private final JpaRepository<E, ID> repository;

    @Override
    public JpaRepository<E, ID> jpaRepo() {
        return repository;
    }

    public interface WithGetAll<E extends BaseEntity<ID>, ID> extends HasJpaRepository<E, ID> {
        @ReadOnlyTransactional
        default List<E> getAll() {
            return jpaRepo().findAll();
        }
    }

    public interface WithSave<E extends BaseEntity<ID>, ID> extends HasJpaRepository<E, ID> {
        @Transactional
        default E save(E entity) {
            return jpaRepo().save(entity);
        }
    }

    public interface WithGetOne<E extends BaseEntity<ID>, ID> extends HasJpaRepository<E, ID> {
        @ReadOnlyTransactional
        default Optional<E> getOne(ID id) {
            return jpaRepo().findById(id);
        }
    }
}
