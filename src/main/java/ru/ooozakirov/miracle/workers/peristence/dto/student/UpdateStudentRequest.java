package ru.ooozakirov.miracle.workers.peristence.dto.student;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.validate.UniqueStudentEmail;
import ru.ooozakirov.miracle.workers.peristence.validate.UniqueStudentPhone;

@Data
@Accessors(chain = true)
public class UpdateStudentRequest {
    @UniqueStudentPhone
    private String phone;
    @Email
    @UniqueStudentEmail
    private String email;
}
