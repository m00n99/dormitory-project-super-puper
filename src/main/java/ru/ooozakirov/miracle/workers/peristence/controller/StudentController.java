package ru.ooozakirov.miracle.workers.peristence.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ooozakirov.miracle.workers.peristence.dto.Error;
import ru.ooozakirov.miracle.workers.peristence.dto.student.*;
import ru.ooozakirov.miracle.workers.peristence.service.StudentService;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createStudent(@Valid @RequestBody CreateStudentRequest request) {
        try {
            log.info("Create student with id {}. Request: {}", request.getStudentId(), objectMapper.writeValueAsString(request));
            studentService.createStudent(request);
            log.info("Success create student with id {}", request.getStudentId());
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error create student with id " + request.getStudentId() + ": ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping(value = "/update/{studentId}")
    public ResponseEntity<HttpStatus> updateStudent(@PathVariable String studentId,
                                                    @Valid @RequestBody UpdateStudentRequest request) {
        try {
            log.info("Update student with id {}. Request {}", studentId, objectMapper.writeValueAsString(request));
            studentService.updateStudent(request, studentId);
            log.info("Success update student with id {}", studentId);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error update student with id " + studentId + " : ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @GetMapping(value = "/get/{studentId}")
    public ResponseEntity<GetStudentResponse> getByStudentId(@PathVariable String studentId) {
        try {
            log.info("Get student request with id {}", studentId);
            var student = studentService.getByStudentId(studentId);
            log.info("Success get student with id {}", studentId);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            var errorMessage = "Error get student with id " + studentId + ": ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().body(new GetStudentResponse()
                    .setError(new Error()
                            .setStatus("error")
                            .setMessage(errorMessage + e.getMessage())));
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @GetMapping(value = "/getAll")
    public ResponseEntity<GetAllStudentsResponse> getAllStudents() {
        try {
            log.info("Get all students request");
            var students = studentService.getAllStudents();
            log.info("Success get all students");
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            var errorMessage = "Error get all students: ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().body(new GetAllStudentsResponse()
                    .setError(new Error()
                            .setStatus("error")
                            .setMessage(errorMessage + e.getMessage())));
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @GetMapping(value = "/getNonResident")
    public ResponseEntity<GetNonResidentStudentResponse> getNonResident() {
        try {
            log.info("Get non resident students request");
            var students = studentService.getNonResident();
            log.info("Success get non resident students");
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            var errorMessage = "Error get non resident students: ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().body(new GetNonResidentStudentResponse()
                    .setError(new Error()
                            .setStatus("error")
                            .setMessage(errorMessage + e.getMessage())));
        }
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping(value = "/uploadPhoto/{studentId}")
    public ResponseEntity<HttpStatus> uploadPhoto(@PathVariable String studentId,
                                                  @RequestPart MultipartFile file) throws IOException {
        try {
            log.info("Upload photo of student request with id {}", studentId);
            studentService.savePhoto(studentId, file);
            log.info("Success upload photo of student with id {}", studentId);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error upload photo of student: ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping(value = "/saveInventory/{studentId}")
    public ResponseEntity<HttpStatus> saveInventory(@PathVariable String studentId,
                                                    @RequestBody SaveStudentInventoryRequest request) {
        try {
            log.info("Save inventory of student request with id {}", studentId);
            studentService.saveInventory(request, studentId);
            log.info("Success save inventory of student with id {}", studentId);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error save inventory of student: ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping(value = "/evict/{studentId}")
    public ResponseEntity<HttpStatus> evict(@PathVariable String studentId) {
        try {
            log.info("Evict student with id {}", studentId);
            studentService.evict(studentId);
            log.info("Success evict student with id {}", studentId);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error evict student: ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
