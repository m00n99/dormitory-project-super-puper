package ru.ooozakirov.miracle.workers.peristence.dto.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.dto.Error;
import ru.ooozakirov.miracle.workers.peristence.model.Gender;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetStudentResponse {
    private String studentId;
    private String firstname;
    private String lastname;
    private String middlename;
    private String phone;
    private String email;
    private Gender gender;
    private Room room;
    private List<String> inventories;
    private Photo photo;
    private Error error;
}

