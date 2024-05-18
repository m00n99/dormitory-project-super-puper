package ru.ooozakirov.miracle.workers.peristence.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.ooozakirov.miracle.workers.peristence.repo.StudentRepository;

@RequiredArgsConstructor
public class UniqueStudentPhoneValidator implements ConstraintValidator<UniqueStudentPhone, String> {
    private final StudentRepository studentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.isEmpty()) {
            return studentRepository.findByPhone(value).isEmpty();
        }

        return true;
    }
}
