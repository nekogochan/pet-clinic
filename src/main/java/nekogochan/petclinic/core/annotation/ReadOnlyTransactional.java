package nekogochan.petclinic.core.annotation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, noRollbackFor = Exception.class)
public @interface ReadOnlyTransactional {
}
