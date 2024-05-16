package ru.ooozakirov.miracle.workers.peristence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ooozakirov.miracle.workers.peristence.dto.student.*;
import ru.ooozakirov.miracle.workers.peristence.model.Document;
import ru.ooozakirov.miracle.workers.peristence.model.DocumentType;
import ru.ooozakirov.miracle.workers.peristence.repo.DocumentRepository;
import ru.ooozakirov.miracle.workers.peristence.repo.StudentRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final StudentRepository studentRepository;
    private final DocumentRepository documentRepository;

    public Document getDocument(String studentId, String filename) {
        var student = studentRepository.findByStudentId(studentId).orElse(null);
        var documents = student.getDocuments();
        return documents.stream().filter(document -> filename.equals(filename)).findAny().orElse(null);
    }

    @Transactional
    public void saveDocuments(String studentId, String documentType, MultipartFile file) throws IOException {
        var student = studentRepository.findByStudentId(studentId).orElse(null);
        var timeAttach = LocalDateTime.now();
        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        documentRepository.save(new Document()
                .setFilename(documentType + " " + format.format(timeAttach))
                .setStudent(student)
                .setType(DocumentType.valueOfLabel(documentType))
                .setMimeType(file.getContentType())
                .setData(file.getBytes()));
    }

    public GetFilenamesResponse getFilenames(String studentId) {
        var student = studentRepository.findByStudentId(studentId).orElse(null);
        var filenames = student.getDocuments().stream().map(Document::getFilename).toList();
        return new GetFilenamesResponse().setFilenames(filenames);
    }
}
