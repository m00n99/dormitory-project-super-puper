package ru.ooozakirov.miracle.workers.peristence.dto.floor;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetFloorResponse {
    private String number;
    private List<Room> rooms;
}
