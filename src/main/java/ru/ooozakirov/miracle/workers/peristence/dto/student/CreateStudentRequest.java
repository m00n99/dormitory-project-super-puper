package ru.ooozakirov.miracle.workers.peristence.dto.student;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.model.Gender;
import ru.ooozakirov.miracle.workers.peristence.validate.UniqueStudentEmail;
import ru.ooozakirov.miracle.workers.peristence.validate.UniqueStudentId;
import ru.ooozakirov.miracle.workers.peristence.validate.UniqueStudentPhone;

@Data
@Accessors(chain = true)
public class CreateStudentRequest {
    @NotEmpty(message = "Id студента не должно быть пустым")
    @UniqueStudentId(message = "Студент с таким id уже существует")
    @Size(max = 9, message = "Кол-во символов не должно превышать 9")
    private String studentId;
    @NotEmpty(message = "Имя студента не должно быть пустым")
    private String firstname;
    @NotEmpty(message = "Фамилия студента не должно быть пустым")
    private String lastname;
    private String middlename;
    @NotEmpty(message = "Телефон студента не должен быть пустым")
    @UniqueStudentPhone(message = "Студент с таким номером телефона уже существует")
    private String phone;
    @Email(message = "Неверный формат email")
    @UniqueStudentEmail(message = "Студент с таким email уже существует")
    private String email;
    @NotNull(message = "Пол студента не должен быть пустым")
    private Gender gender;
}
