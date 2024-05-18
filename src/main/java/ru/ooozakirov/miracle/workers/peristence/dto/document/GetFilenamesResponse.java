package ru.ooozakirov.miracle.workers.peristence.dto.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.dto.Error;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetFilenamesResponse {
    private List<String> filenames;
    private Error error;
}
