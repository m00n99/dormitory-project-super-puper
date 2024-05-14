package ru.ooozakirov.miracle.workers.peristence.dto.floor;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private String studentId;
    private String firstname;
    private String lastname;
    private String middlename;
}
