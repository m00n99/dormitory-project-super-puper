package ru.ooozakirov.miracle.workers.peristence.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ooozakirov.miracle.workers.peristence.dto.student.CreateStudentRequest;
import ru.ooozakirov.miracle.workers.peristence.dto.student.GetNonResidentStudentResponse;
import ru.ooozakirov.miracle.workers.peristence.dto.student.GetStudentResponse;
import ru.ooozakirov.miracle.workers.peristence.dto.student.UpdateStudentRequest;
import ru.ooozakirov.miracle.workers.peristence.service.StudentService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> create(@RequestBody CreateStudentRequest request) {
        studentService.create(request);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping(value = "/update/{studentId}")
    public ResponseEntity<HttpStatus> update(@PathVariable String studentId,
                                             @RequestBody UpdateStudentRequest request) throws IOException {
        studentService.update(request, studentId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @GetMapping(value = "/get/{studentId}")
    public ResponseEntity<GetStudentResponse> getByStudentId(@PathVariable String studentId) {
        return ResponseEntity.ok(studentService.getByStudentId(studentId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @GetMapping(value = "/getNonResident")
    public ResponseEntity<GetNonResidentStudentResponse> getNonResident() {
        return ResponseEntity.ok(studentService.getNonResident());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @GetMapping(value = "/getPhoto/{studentId}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String studentId) {
        var photo = studentService.getPhoto(studentId);
        var body = new ByteArrayResource(photo.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, photo.getMimeType())
                .body(body);
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping(value = "/uploadPhoto/{studentId}")
    public ResponseEntity<HttpStatus> uploadPhoto(@PathVariable String studentId,
                                                  @RequestPart MultipartFile file) throws IOException {
        studentService.savePhoto(studentId, file);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @GetMapping(value = "/getDocument/{studentId}/{filename}")
    public ResponseEntity<Resource> getDocuments(@PathVariable String studentId, @PathVariable String filename) {
        var document = studentService.getDocument(studentId, filename);
        var body = new ByteArrayResource(document.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, document.getMimeType())
                .body(body);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping(value = "/attachDocuments/{studentId}")
    public ResponseEntity<HttpStatus> attachDocuments(@PathVariable String studentId,
                                                      @RequestPart List<MultipartFile> files) throws IOException {
        studentService.saveDocuments(studentId, files);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
