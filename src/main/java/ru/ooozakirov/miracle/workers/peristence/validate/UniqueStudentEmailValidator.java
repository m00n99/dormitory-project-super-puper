package ru.ooozakirov.miracle.workers.peristence.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.ooozakirov.miracle.workers.peristence.repo.StudentRepository;

@RequiredArgsConstructor
public class UniqueStudentEmailValidator implements ConstraintValidator<UniqueStudentEmail, String> {
    private final StudentRepository studentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.isEmpty()) {
            return studentRepository.findByEmail(value).isEmpty();
        }

        return true;
    }
}
