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
import ru.ooozakirov.miracle.workers.peristence.dto.student.*;
import ru.ooozakirov.miracle.workers.peristence.service.DocumentService;
import ru.ooozakirov.miracle.workers.peristence.service.StudentService;

import java.io.IOException;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @GetMapping(value = "/get/{studentId}/{filename}")
    public ResponseEntity<Resource> getDocuments(@PathVariable String studentId, @PathVariable String filename) {
        var document = documentService.getDocument(studentId, filename);
        var body = new ByteArrayResource(document.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, document.getMimeType())
                .body(body);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @PostMapping(value = "/attach/{studentId}/{documentType}")
    public ResponseEntity<HttpStatus> attachDocuments(@PathVariable String studentId,
                                                      @PathVariable String documentType,
                                                      @RequestPart MultipartFile file) throws IOException {
        documentService.saveDocuments(studentId, documentType, file);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT', 'STUDENT')")
    @GetMapping(value = "/getFilename/{studentId}")
    public ResponseEntity<GetFilenamesResponse> getFilenameDocuments(@PathVariable String studentId) {
        return ResponseEntity.ok(documentService.getFilenames(studentId));
    }
}
