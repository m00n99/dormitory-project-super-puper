package ru.ooozakirov.miracle.workers.peristence.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ooozakirov.miracle.workers.peristence.service.RoomService;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @PostMapping("/attach/{studentId}/{roomNumber}")
    public ResponseEntity<HttpStatus> attach(@PathVariable String studentId,
                                             @PathVariable String roomNumber){
        roomService.attach(studentId, roomNumber);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
