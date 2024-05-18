package ru.ooozakirov.miracle.workers.peristence.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ooozakirov.miracle.workers.peristence.service.RoomService;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Slf4j
public class RoomController {
    private final RoomService roomService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping("/attach/{studentId}/{roomNumber}")
    public ResponseEntity<HttpStatus> attach(@PathVariable String studentId,
                                             @PathVariable String roomNumber){
        try {
            log.info("Attach student with id {} in room {}", studentId, roomNumber);
            roomService.attach(studentId, roomNumber);
            log.info("Success attach student with id {} in room {}", studentId, roomNumber);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error attach student with id " + studentId + " in room " + roomNumber + ": ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping("/unassign/{studentId}")
    public ResponseEntity<HttpStatus> unassign(@PathVariable String studentId){
        try {
            log.info("Unassign student with id {} from room", studentId);
            roomService.unassign(studentId);
            log.info("Success unassign student with id {} from room", studentId);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error unassign student with id " + studentId + " from room: ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
