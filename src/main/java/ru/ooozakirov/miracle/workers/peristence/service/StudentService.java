package ru.ooozakirov.miracle.workers.peristence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ooozakirov.miracle.workers.peristence.dto.student.*;
import ru.ooozakirov.miracle.workers.peristence.mapper.StudentMapper;
import ru.ooozakirov.miracle.workers.peristence.mapper.UserMapper;
import ru.ooozakirov.miracle.workers.peristence.model.*;
import ru.ooozakirov.miracle.workers.peristence.model.Photo;
import ru.ooozakirov.miracle.workers.peristence.repo.DocumentRepository;
import ru.ooozakirov.miracle.workers.peristence.repo.InventoryRepository;
import ru.ooozakirov.miracle.workers.peristence.repo.StudentRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RoomService roomService;
    private final InventoryRepository inventoryRepository;
    private final StudentMapper studentMapper;
    private final UserMapper userMapper;

    @Transactional
    public void createStudent(CreateStudentRequest request) {
        var user = userMapper.mapStudentRequestToUser(request);
        var student = studentMapper.mapCreateStudentRequestToStudent(request);
        student.setUser(user);
        studentRepository.save(student);
    }

    public GetStudentResponse getByStudentId(String studentId) {
        var student = studentRepository.findByStudentId(studentId).orElseThrow();

        return studentMapper.mapStudentToGetStudentResponse(student);
    }

    public GetNonResidentStudentResponse getNonResident() {
        var students = studentRepository.findAll().stream().filter(s -> Objects.isNull(s.getRoom())).toList();
        return new GetNonResidentStudentResponse().setStudents(studentMapper.mapModelStudentsToStudents(students));
    }

    @Transactional
    public void updateStudent(UpdateStudentRequest request, String studentId) throws IOException {
        var student = studentRepository.findByStudentId(studentId).orElseThrow();
        studentMapper.updateStudentFromDto(student, request);
        studentRepository.save(student);
    }

    @Transactional
    public void savePhoto(String studentId, MultipartFile file) throws IOException {
        var student = studentRepository.findByStudentId(studentId).orElseThrow();
        student.setPhoto(new Photo()
                .setFilename(file.getOriginalFilename())
                .setMimeType(file.getContentType())
                .setData(file.getBytes()));
        studentRepository.save(student);
    }

    public Photo getPhoto(String studentId) {
        return studentRepository.findByStudentId(studentId).map(Student::getPhoto).orElseThrow();
    }

    public GetAllStudentsResponse getAllStudents() {
        var students = studentRepository.findAll()
                .stream().map(studentMapper::mapStudentToGetStudentResponse).toList();
        return new GetAllStudentsResponse()
                .setStudents(students);
    }

    @Transactional
    public void saveInventory(SaveStudentInventoryRequest request, String studentId) {
        var student = studentRepository.findByStudentId(studentId).orElseThrow();
        var inventories = Optional.ofNullable(student.getInventories());
        if (inventories.isEmpty()) {
            addListInventories(request, student);
            return;
        }
        for (Inventory inventory : inventories.get()){
            inventoryRepository.delete(inventory);
        }
        addListInventories(request, student);
    }

    private void addListInventories (SaveStudentInventoryRequest request, Student student) {
        for (String name : request.getInventories()) {
            inventoryRepository.save(new Inventory()
                    .setStudent(student)
                    .setType(InventoryType.valueOfLabel(name)));
        }
    }

    @Transactional
    public void evict(String studentId) {
        var student = studentRepository.findByStudentId(studentId).orElseThrow();
        student.setStatus(StatusStudent.EVICTED);
        roomService.unassign(studentId);
    }
}
