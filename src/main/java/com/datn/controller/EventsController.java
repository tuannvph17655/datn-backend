package com.datn.controller;

import com.datn.service.EventsService;
import com.datn.utils.base.PuddyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
@Slf4j
public class EnventController extends PuddyController {

	@Autowired
	EventsService eventsService;

	@GetMapping("")
	public ResponseEntity<?> getAllEvent(){
		log.info("START API /api/v1/size");
		return ResponseEntity.status(HttpStatus.OK).body(service.eventService.getAllEvent());
	}
}
