package com.example.homework50.controllers;

import com.example.homework50.dto.UserDto;
import com.example.homework50.service.SubscribeService;
import com.example.homework50.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SubscribeController {
    private SubscribeService subscribeService;
    private UserService userService;

    @PostMapping("/subscribe/add")
    public ResponseEntity<String> subscription(Authentication authentication,
                                               @RequestParam Long subTo) {
        String email = authentication.getName();

        if(email != null){
            UserDto userDto = userService.getUserByEmail(email).get(0);
            return new ResponseEntity<>(subscribeService.addSubscription(userDto.getUserId(), subTo), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }
}
