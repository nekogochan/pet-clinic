package nekogochan.petclinic.core.crud.beanpostprocessor;

import nekogochan.petclinic.core.crud.controller.CrudController;
import nekogochan.petclinic.core.crud.service.CrudService;
import nekogochan.petclinic.core.entity.BaseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

class CrudDependsOnBeanPostProcessorTest {

    CrudDependsOnBeanPostProcessor crudDependsOnBeanPostProcessor = new CrudDependsOnBeanPostProcessor();

    class Entity implements BaseEntity<Long> {
        @Override
        public Long getId() {
            return null;
        }
    }

    interface ServiceMixin {

    }

    @CrudDependsOn(ServiceMixin.class)
    interface ControllerMixin {
    }

    @Test
    @DisplayName("postProcessBeforeInitialization should throw exception, " +
                 "if service implement interface that controller depends on")
    void postProcessFail() {
        class Service extends CrudService<Entity, Long> {
            public Service(JpaRepository<Entity, Long> repository) {
                super(repository);
            }
        }

        class Controller extends CrudController<Entity, Long> implements ControllerMixin {
            public Controller(CrudService<Entity, Long> service) {
                super(service);
            }
        }

        var bean = new Controller(new Service(null));

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> crudDependsOnBeanPostProcessor.postProcessBeforeInitialization(bean, "bean")
        );
    }

    @Test
    @DisplayName("postProcessBeforeInitialization should do nothing," +
                 "if service implement interface that controller depends on")
    void postProcessSuccess() {
        class Service extends CrudService<Entity, Long> implements ServiceMixin {
            public Service(JpaRepository<Entity, Long> repository) {
                super(repository);
            }
        }

        class Controller extends CrudController<Entity, Long> implements ControllerMixin {
            public Controller(CrudService<Entity, Long> service) {
                super(service);
            }
        }

        var bean = new Controller(new Service(null));

        Assertions.assertDoesNotThrow(
                () -> crudDependsOnBeanPostProcessor.postProcessBeforeInitialization(bean, "bean")
        );
    }
}