package ru.ooozakirov.miracle.workers.peristence.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ooozakirov.miracle.workers.peristence.dto.floor.GetFloorResponse;
import ru.ooozakirov.miracle.workers.peristence.service.FloorService;

@RestController
@RequestMapping("/floor")
@RequiredArgsConstructor
public class FloorController {
    private final FloorService floorService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @GetMapping("/getCount")
    public Integer getCount(){
        return floorService.getCount();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMMANDANT')")
    @GetMapping("/get/{number}")
    public GetFloorResponse getFloor(@PathVariable String number){
        return floorService.getFloor(number);
    }
}
