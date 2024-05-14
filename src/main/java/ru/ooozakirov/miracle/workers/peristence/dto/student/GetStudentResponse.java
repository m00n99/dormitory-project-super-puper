package ru.ooozakirov.miracle.workers.peristence.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.model.Gender;

@Data
@Accessors(chain = true)
public class GetStudentResponse {
    private String studentId;
    private String firstname;
    private String lastname;
    private String middlename;
    private String phone;
    private String email;
    private Gender gender;
    private Room room;
}

