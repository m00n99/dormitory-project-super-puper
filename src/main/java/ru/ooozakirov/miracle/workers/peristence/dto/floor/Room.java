package ru.ooozakirov.miracle.workers.peristence.dto.floor;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.dto.student.Floor;

import java.util.List;

@Data
@Accessors(chain = true)
public class Room {
    private String number;
    private Integer maxCountStudent;
    private List<Student> students;
}
