package ru.ooozakirov.miracle.workers.peristence.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.ooozakirov.miracle.workers.peristence.repo.StudentRepository;

@RequiredArgsConstructor
public class UniqueStudentIdValidator implements ConstraintValidator<UniqueStudentId, String> {
    private final StudentRepository studentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.isEmpty()) {
            return studentRepository.findByStudentId(value).isEmpty();
        }

        return true;
    }
}
