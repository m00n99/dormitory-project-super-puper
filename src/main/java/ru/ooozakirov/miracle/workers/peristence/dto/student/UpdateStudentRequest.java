package ru.ooozakirov.miracle.workers.peristence.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateStudentRequest {
    private String firstname;
    private String lastname;
    private String middlename;
    private String phone;
    private String email;
}
