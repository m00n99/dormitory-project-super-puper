package ru.ooozakirov.miracle.workers.peristence.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetAllStudentsResponse {
    private List<GetStudentResponse> students;
}
