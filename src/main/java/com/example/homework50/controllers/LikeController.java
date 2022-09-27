package com.example.homework50.controllers;

import com.example.homework50.main.Like;
import com.example.homework50.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class LikeController {
    private LikeService likeService;
    @GetMapping("/like/on/from/{pubId}/{userId}")
    public ResponseEntity<Boolean> isLiked(@PathVariable Long pubId,@PathVariable Long userId){
        return new ResponseEntity<>(likeService.isLikedServ(pubId,userId), HttpStatus.OK);
    }

}
