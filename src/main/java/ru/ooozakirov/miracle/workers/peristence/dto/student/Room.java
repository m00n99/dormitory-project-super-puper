package ru.ooozakirov.miracle.workers.peristence.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Room {
    private String number;
    private Floor floor;
    private Integer maxCountStudent;
}
