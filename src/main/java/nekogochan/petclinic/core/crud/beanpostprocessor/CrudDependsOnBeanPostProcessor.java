package nekogochan.petclinic.core.crud.beanpostprocessor;

import nekogochan.petclinic.core.crud.controller.HasCrudService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Component
public class CrudDependsOnBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var beanClass = bean.getClass();
        forEachInterfaceWithAnnotationCrudDependsOn(beanClass, (javaClass) -> check(javaClass, bean));
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    private void forEachInterfaceWithAnnotationCrudDependsOn(Class<?> root, Consumer<Class<?>> fn) {
        var superClass = root.getSuperclass();
        var interfaces = root.getInterfaces();
        Stream.concat(Stream.of(superClass), Arrays.stream(interfaces))
                .filter(Objects::nonNull)
                .forEach(javaClass -> {
                    var crudDependsOnAnnotation = javaClass.getAnnotation(CrudDependsOn.class);
                    if (crudDependsOnAnnotation != null) {
                        fn.accept(javaClass);
                    }
                    forEachInterfaceWithAnnotationCrudDependsOn(javaClass, fn);
                });
    }

    private void check(Class<?> javaClass, Object bean) {
        var crudDependsOnAnnotation = javaClass.getAnnotation(CrudDependsOn.class);
        var dependedClass = crudDependsOnAnnotation.value();
        if (!ResolvableType.forClass(HasCrudService.class).isAssignableFrom(bean.getClass())) {
            throw error(bean, "bean should be inheritor of " + HasCrudService.class.getName());
        }
        var hasCrudService = (HasCrudService) bean;
        var crudService = hasCrudService.crudService();
        var crudServiceClass = crudService.getClass();

        if (!ResolvableType.forClass(dependedClass).isAssignableFrom(crudServiceClass)) {
            throw error(bean, "bean " + crudServiceClass.getName() + " should implement " + dependedClass.getName());
        }
    }

    private IllegalStateException error(Object bean, String reason) {
        return new IllegalStateException("On check class " + bean.getClass().getName() + ":" + System.lineSeparator() + reason);
    }
}
