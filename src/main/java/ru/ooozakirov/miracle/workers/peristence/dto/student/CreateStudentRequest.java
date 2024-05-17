package ru.ooozakirov.miracle.workers.peristence.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.model.Gender;

@Data
@Accessors(chain = true)
public class CreateStudentRequest {
    @NotEmpty(message = "Id студента не должно быть пустым")
    private String studentId;
    @NotEmpty(message = "Имя студента не должно быть пустым")
    private String firstname;
    @NotEmpty(message = "Фамилия студента не должно быть пустым")
    private String lastname;
    private String middlename;
    @NotEmpty(message = "Телефон студента не должен быть пустым")
    private String phone;
    @Email(message = "Неверный формат email")
    private String email;
    @NotNull(message = "Пол студента не должен быть пустым")
    private Gender gender;
}
