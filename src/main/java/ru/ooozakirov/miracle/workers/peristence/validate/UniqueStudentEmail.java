package ru.ooozakirov.miracle.workers.peristence.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueStudentEmailValidator.class)
@Documented
public @interface UniqueStudentEmail {
    String  message() default "{UniqueStudentEmail.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
