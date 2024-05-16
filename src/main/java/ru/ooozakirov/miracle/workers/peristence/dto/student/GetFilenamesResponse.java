package ru.ooozakirov.miracle.workers.peristence.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetFilenamesResponse {
    private List<String> filenames;
}
