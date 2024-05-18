package ru.ooozakirov.miracle.workers.peristence.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ooozakirov.miracle.workers.peristence.dto.Error;
import ru.ooozakirov.miracle.workers.peristence.dto.floor.GetFloorResponse;
import ru.ooozakirov.miracle.workers.peristence.service.FloorService;

@RestController
@RequestMapping("/floor")
@RequiredArgsConstructor
@Slf4j
public class FloorController {
    private final FloorService floorService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @GetMapping("/getCount")
    public Integer getCount() {
        try {
            log.info("Get floor count request");
            var floorCount = floorService.getCount();
            log.info("Success get floor count");
            return floorCount;
        } catch (Exception e) {
            var errorMessage = "Error get floor count";
            log.error(errorMessage, e);
            return 0;
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @GetMapping("/get/{number}")
    public ResponseEntity<GetFloorResponse> getFloor(@PathVariable String number) {
        try {
            log.info("Get floor {} request", number);
            var floor = floorService.getFloor(number);
            log.info("Success get floor {}", number);
            return ResponseEntity.ok(floor);
        } catch (Exception e) {
            var errorMessage = "Error get floor " + number + ": ";
            log.error(errorMessage, e);
            return ResponseEntity.internalServerError().body(new GetFloorResponse()
                    .setError(new Error()
                            .setStatus("error")
                            .setMessage(errorMessage + e.getMessage())));
        }
    }
}
