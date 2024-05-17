package ru.ooozakirov.miracle.workers.peristence.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Error {
    private String status;
    private String message;
}
