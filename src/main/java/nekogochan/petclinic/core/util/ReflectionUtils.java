package nekogochan.petclinic.core.util;

import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.stream.Stream;

@UtilityClass
public class ReflectionUtils {
    public static Stream<Class<?>> classesWithAnnotation(Class<?> root, Class<? extends Annotation> annotationClass) {
        var superClass = root.getSuperclass();
        var interfaces = root.getInterfaces();
        var stream = Arrays.stream(interfaces);
        if (superClass != null) {
            stream = Stream.concat(Stream.of(superClass), stream);
        }
        for (var i : interfaces) {
            stream = Stream.concat(stream, classesWithAnnotation(i, annotationClass));
        }
        return stream.filter(cl -> cl.getAnnotation(annotationClass) != null);
    }

}
