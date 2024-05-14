package ru.ooozakirov.miracle.workers.peristence.mapper;

import org.mapstruct.Mapper;
import ru.ooozakirov.miracle.workers.peristence.dto.floor.GetFloorResponse;
import ru.ooozakirov.miracle.workers.peristence.dto.student.CreateStudentRequest;
import ru.ooozakirov.miracle.workers.peristence.dto.student.GetStudentResponse;
import ru.ooozakirov.miracle.workers.peristence.model.Floor;
import ru.ooozakirov.miracle.workers.peristence.model.Student;

@Mapper(componentModel = "spring")
public interface FloorMapper {

    GetFloorResponse mapFloorToGetFloorResponse(Floor floor);
}
