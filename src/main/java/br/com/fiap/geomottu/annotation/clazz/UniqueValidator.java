package br.com.fiap.geomottu.annotation.clazz;

import br.com.fiap.geomottu.annotation.interfaces.Unique;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String fieldName;
    private Class<?> domainClass;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        String jpql = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :value",
                domainClass.getSimpleName(), fieldName);

        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("value", value)
                .getSingleResult();

        return count == 0;
    }
}
