package ru.ooozakirov.miracle.workers.peristence.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueStudentPhoneValidator.class)
@Documented
public @interface UniqueStudentPhone {
    String  message() default "{UniqueStudentPhone.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
