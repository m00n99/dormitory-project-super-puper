package ru.ooozakirov.miracle.workers.peristence.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.model.Gender;

import java.util.List;

@Data
@Accessors(chain = true)
public class SaveStudentInventoryRequest {
    private List<String> inventories;
}
