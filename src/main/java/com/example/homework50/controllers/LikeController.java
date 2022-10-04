package com.example.homework50.controllers;

import com.example.homework50.dto.UserDto;
import com.example.homework50.main.User;
import com.example.homework50.service.LikeService;
import com.example.homework50.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
public class LikeController {
    private LikeService likeService;
    private UserService userService;
    @GetMapping("/like/on/from/{pubId}/{userId}")
    public ResponseEntity<?> isLiked(Authentication authentication, @PathVariable Long pubId, @PathVariable Long userId) {
        String email = authentication.getName();
        if(email != null){
            return new ResponseEntity<>(likeService.isLikedServ(pubId, userId), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<String> like(Authentication authentication,
                                       @RequestParam Long pubId) {
        String email = authentication.getName();
        if(email != null){
            UserDto userDto = userService.getUserByEmail(email).get(0);
            return new ResponseEntity<>(likeService.like(userDto.getUserId(), pubId), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }

}
