package com.example.homework50.controllers;

import com.example.homework50.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class LikeController {
    private LikeService likeService;

    @GetMapping("/like/on/from/{pubId}/{userId}")
    public ResponseEntity<?> isLiked(@PathVariable Long pubId, @PathVariable Long userId) {
        if(!"0".equals(userId)){
            return new ResponseEntity<>(likeService.isLikedServ(pubId, userId), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<String> like(@CookieValue(value = "userId", required = false, defaultValue = "0") String userId,
                                       @RequestParam Long pubId) {
        Long id =  Long.parseLong(userId);

        if(!"0".equals(userId)){
            return new ResponseEntity<>(likeService.like(id, pubId), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }

}
