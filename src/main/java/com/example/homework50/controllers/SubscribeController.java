package com.example.homework50.controllers;

import com.example.homework50.service.SubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SubscribeController {
    private SubscribeService subscribeService;

    @PostMapping("/subscribe/add")
    public ResponseEntity<String> subscription(@CookieValue(value = "userId", required = false, defaultValue = "0") String userId,
                                       @RequestParam Long subTo) {
        Long id =  Long.parseLong(userId);
        if(!"0".equals(userId)){
            return new ResponseEntity<>(subscribeService.addSubscription(id, subTo), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }
}
