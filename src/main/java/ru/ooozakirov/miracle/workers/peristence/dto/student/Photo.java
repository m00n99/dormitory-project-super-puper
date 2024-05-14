package ru.ooozakirov.miracle.workers.peristence.dto.student;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.model.Gender;

@Data
@Accessors(chain = true)
public class Photo {
    private String filename;
    private String mimeType;
    private byte[] data;
}
