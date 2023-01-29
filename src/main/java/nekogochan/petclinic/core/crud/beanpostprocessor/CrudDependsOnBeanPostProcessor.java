package nekogochan.petclinic.core.crud.beanpostprocessor;

import nekogochan.petclinic.core.crud.controller.HasCrudService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import static nekogochan.petclinic.core.util.ReflectionUtils.classesWithAnnotation;

@Component
public class CrudDependsOnBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var beanClass = bean.getClass();
        classesWithAnnotation(beanClass, CrudDependsOn.class).forEach(javaClass -> check(javaClass, bean));
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    private void check(Class<?> javaClass, Object bean) {
        var dependedClass = javaClass.getAnnotation(CrudDependsOn.class).value();
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
