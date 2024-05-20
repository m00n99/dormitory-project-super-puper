package ru.ooozakirov.miracle.workers.peristence.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ooozakirov.miracle.workers.peristence.dto.Error;
import ru.ooozakirov.miracle.workers.peristence.dto.document.GetFilenamesResponse;
import ru.ooozakirov.miracle.workers.peristence.service.DocumentService;

import java.io.IOException;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {
    private final DocumentService documentService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @GetMapping(value = "/download/{studentId}/{filename}")
    public ResponseEntity<Resource> getDocuments(@PathVariable String studentId, @PathVariable String filename) {
        try {
            log.info("Download document student with id {}, filename {}", studentId, filename);
            var document = documentService.getDocument(studentId, filename);
            var body = new ByteArrayResource(document.getData());
            log.info("Success download document student with id {}, filename {}", studentId, filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, document.getMimeType())
                    .body(body);
        } catch (Exception e) {
            var errorMessage = "Error download document student with id " + studentId + ", filename " + filename + ": ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping(value = "/attach/{studentId}/{documentType}")
    public ResponseEntity<HttpStatus> attachDocuments(@PathVariable String studentId,
                                                      @PathVariable String documentType,
                                                      @RequestPart MultipartFile file) {
        try {
            log.info("Attach document student with id {}, documentType {}", studentId, documentType);
            documentService.saveDocuments(studentId, documentType, file);
            log.info("Success attach document student with id {}, documentType {}", studentId, documentType);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error attach document student with id " + studentId + ", documentType " + documentType + ": ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @GetMapping(value = "/getFilename/{studentId}")
    public ResponseEntity<GetFilenamesResponse> getFilenameDocuments(@PathVariable String studentId) {
        try {
            log.info("Get filenames student with id {}", studentId);
            var filenames = documentService.getFilenames(studentId);
            log.info("Success get filenames student with id {}", studentId);
            return ResponseEntity.ok(filenames);
        } catch (Exception e) {
            var errorMessage = "Error filename student with id " + studentId + ": ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().body(new GetFilenamesResponse()
                    .setError(new Error()
                            .setStatus("error")
                            .setMessage(errorMessage + e.getMessage())));
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping(value = "/delete/{studentId}/{filename}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String studentId, @PathVariable String filename) {
        try {
            log.info("Delete document student with id {}, filename {}", studentId, filename);
            documentService.delete(studentId, filename);
            log.info("Success delete document student with id {}, filename {}", studentId, filename);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error delete document student with id " + studentId + ", filename " + filename + ": ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
