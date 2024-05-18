package ru.ooozakirov.miracle.workers.peristence.dto.floor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.ooozakirov.miracle.workers.peristence.dto.Error;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetFloorResponse {
    private String number;
    private List<Room> rooms;
    private Error error;
}
