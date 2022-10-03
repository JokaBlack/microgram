package com.example.homework50.controllers;

import com.example.homework50.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/comment/add")
    public ResponseEntity<?> addComment(@CookieValue(value = "userId", required = false, defaultValue = "0") String userId,
                                        @RequestParam String commText, @RequestParam int pubId){
        if(!"0".equals(userId)){
            return new ResponseEntity<>(commentService.addComment(pubId, commText), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);

    }

    @PostMapping("/comment/delete")
    public ResponseEntity<?> addComment(@CookieValue(value = "userId", required = false, defaultValue = "0") String userId,
                                        @RequestParam int pubId , @RequestParam int commId){
        int id = Integer.parseInt(userId);

        if(!"0".equals(userId)){
            return new ResponseEntity<>(commentService.deleteComment(id, pubId , commId), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);

    }
}
