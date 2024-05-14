package ru.ooozakirov.miracle.workers.peristence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ooozakirov.miracle.workers.peristence.dto.student.CreateStudentRequest;
import ru.ooozakirov.miracle.workers.peristence.dto.student.GetNonResidentStudentResponse;
import ru.ooozakirov.miracle.workers.peristence.dto.student.GetStudentResponse;
import ru.ooozakirov.miracle.workers.peristence.dto.student.UpdateStudentRequest;
import ru.ooozakirov.miracle.workers.peristence.mapper.StudentMapper;
import ru.ooozakirov.miracle.workers.peristence.mapper.UserMapper;
import ru.ooozakirov.miracle.workers.peristence.model.Document;
import ru.ooozakirov.miracle.workers.peristence.model.DocumentType;
import ru.ooozakirov.miracle.workers.peristence.model.Photo;
import ru.ooozakirov.miracle.workers.peristence.repo.StudentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserMapper userMapper;

    @Transactional
    public void create(CreateStudentRequest request) {
        var user = userMapper.mapStudentRequestToUser(request);
        var student = studentMapper.mapCreateStudentRequestToStudent(request);
        student.setUser(user);
        studentRepository.save(student);
    }

    public GetStudentResponse getByStudentId(String studentId) {
        var student = studentRepository.findByStudentId(studentId).orElse(null);

        return studentMapper.mapStudentToGetStudentResponse(student);
    }

    public GetNonResidentStudentResponse getNonResident() {
        var students = studentRepository.findAll().stream().filter(s -> Objects.isNull(s.getRoom())).toList();
        return new GetNonResidentStudentResponse().setStudents(studentMapper.mapModelStudentsToStudents(students));
    }

    @Transactional
    public void update(UpdateStudentRequest request, String studentId) throws IOException {
        var student = studentRepository.findByStudentId(studentId).orElse(null);
        studentMapper.updateStudentFromDto(student, request);
        studentRepository.save(student);
    }

    @Transactional
    public void savePhoto(String studentId, MultipartFile file) throws IOException {
        var student = studentRepository.findByStudentId(studentId).orElse(null);
        student.setPhoto(new Photo()
                .setFilename(file.getOriginalFilename())
                .setMimeType(file.getContentType())
                .setData(file.getBytes()));
        studentRepository.save(student);
    }

    public Photo getPhoto(String studentId) {
        var student = studentRepository.findByStudentId(studentId).orElse(null);

        return student.getPhoto();
    }

    public Document getDocument(String studentId, String filename) {
        var student = studentRepository.findByStudentId(studentId).orElse(null);
        var documents = student.getDocuments();
        return documents.stream().filter(document -> document.getFilename().equals(filename)).findAny().orElse(null);
    }

    @Transactional
    public void saveDocuments(String studentId, List<MultipartFile> files) throws IOException {
        var student = studentRepository.findByStudentId(studentId).orElse(null);
        List<Document> documents = new ArrayList<>();
        documents.add(new Document()
                .setFilename("Паспорт")
                .setType(DocumentType.PASSPORT_RF)
                .setMimeType(files.get(0).getContentType())
                .setData(files.get(0).getBytes()));
        student.setDocuments(documents);
        studentRepository.save(student);
    }
}
