package com.datn.controller;

import com.datn.utils.base.PuddyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/suggest")
@RequiredArgsConstructor
@Slf4j
public class SuggestController extends PuddyController {
    @GetMapping("")
    public ResponseEntity<?> getAllSuggeest() {
        log.info("START API /api/v1/suggest");
        return ResponseEntity.status(HttpStatus.OK).body(service.suggestService.getListSuggest());
    }
}
