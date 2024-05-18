package ru.ooozakirov.miracle.workers.peristence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ooozakirov.miracle.workers.peristence.dto.floor.GetFloorResponse;
import ru.ooozakirov.miracle.workers.peristence.dto.student.CreateStudentRequest;
import ru.ooozakirov.miracle.workers.peristence.dto.student.GetStudentResponse;
import ru.ooozakirov.miracle.workers.peristence.mapper.FloorMapper;
import ru.ooozakirov.miracle.workers.peristence.mapper.StudentMapper;
import ru.ooozakirov.miracle.workers.peristence.mapper.UserMapper;
import ru.ooozakirov.miracle.workers.peristence.model.Room;
import ru.ooozakirov.miracle.workers.peristence.repo.FloorRepository;
import ru.ooozakirov.miracle.workers.peristence.repo.StudentRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class FloorService {
    private final FloorRepository floorRepository;
    private final StudentRepository studentRepository;
    private final FloorMapper floorMapper;

    public Integer getCount() {
        var floors = floorRepository.findAll();
        return floors.size();
    }

    public GetFloorResponse getFloor(String number) {
        var floor = floorRepository.findByNumber(number).orElseThrow();
        return floorMapper.mapFloorToGetFloorResponse(floor);
    }
}
