package ru.ooozakirov.miracle.workers.peristence.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Photo {
    private String mimeType;
    private byte[] data;
}
