package ru.ooozakirov.miracle.workers.peristence.dto.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.dto.Error;
import ru.ooozakirov.miracle.workers.peristence.dto.floor.Student;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetNonResidentStudentResponse {
    private List<Student> students;
    private Error error;
}
