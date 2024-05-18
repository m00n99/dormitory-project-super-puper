package ru.ooozakirov.miracle.workers.peristence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ooozakirov.miracle.workers.peristence.dto.floor.GetFloorResponse;
import ru.ooozakirov.miracle.workers.peristence.mapper.FloorMapper;
import ru.ooozakirov.miracle.workers.peristence.repo.FloorRepository;
import ru.ooozakirov.miracle.workers.peristence.repo.RoomRepository;
import ru.ooozakirov.miracle.workers.peristence.repo.StudentRepository;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public void attach(String studentId, String roomNumber) {
        var student = studentRepository.findByStudentId(studentId).orElseThrow();
        var room = roomRepository.findByNumber(roomNumber).orElseThrow();
        student.setRoom(room);
        studentRepository.save(student);
    }
}
