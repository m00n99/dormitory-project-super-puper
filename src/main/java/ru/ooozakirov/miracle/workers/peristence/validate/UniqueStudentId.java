package ru.ooozakirov.miracle.workers.peristence.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueStudentIdValidator.class)
@Documented
public @interface UniqueStudentId {
    String  message() default "{UniqueStudentId.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
