package com.datn.controller.admin;

import com.datn.utils.base.PuddyController;
import com.datn.utils.base.enum_dto.EventDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/event")
@RequiredArgsConstructor
@Slf4j
public class AdminEventController extends PuddyController {


    @ApiOperation(value = "API get all Event")
    @GetMapping("")
    public ResponseEntity<?> getAllEvents() {
        log.info("START API /api/v1/event");
        return ResponseEntity.status(HttpStatus.OK).body(service.eventService.getAllEvent());
    }

    @ApiOperation(value = "API create event")
    @PostMapping("/create")
    public ResponseEntity<?> createNewEvent(@RequestBody EventDto dto) {
        log.info("START API /create");
        return ResponseEntity.ok(service.eventService.create(getCurrentUser(), dto));
    }

    @ApiOperation(value = "API update event")
    @PostMapping("/update")
    public ResponseEntity<?> updateNewEvent(@RequestBody EventDto dto) {
        log.info("START API /update");
        return ResponseEntity.ok(service.eventService.update(getCurrentUser(), dto));
    }

}
