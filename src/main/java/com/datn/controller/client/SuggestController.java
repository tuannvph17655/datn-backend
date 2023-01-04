package com.datn.controller.client;


import com.datn.dto.customer.suggest.SuggestDto;
import com.datn.utils.base.PuddyService;
import com.datn.utils.base.rest.ResData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/suggest")
@RequiredArgsConstructor
public class SuggestController {

    private final PuddyService service;

//    @GetMapping("/category-list")
//    public ResponseEntity<Object> getCategories() {
//        return ResponseEntity.ok(ResData.ok(service.suggestService.getCategories()));
//    }

    @GetMapping("/search")
    public ResponseEntity<Object> getSizeAvailable(SuggestDto dto) {
        return ResponseEntity.ok(service.suggestService.getSizeAvailable(dto));
    }

}
