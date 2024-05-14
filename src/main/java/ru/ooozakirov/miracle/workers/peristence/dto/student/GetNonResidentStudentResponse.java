package ru.ooozakirov.miracle.workers.peristence.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.dto.floor.Student;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetNonResidentStudentResponse {
    private List<Student> students;
}
