package com.example.homework50.controllers;

import com.example.homework50.dto.UserDto;
import com.example.homework50.service.CommentService;
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
public class CommentController {
    private CommentService commentService;
    private UserService userService;

    @PostMapping("/comment/add")
    public ResponseEntity<?> addComment(Authentication authentication,
                                        @RequestParam String commText, @RequestParam int pubId){
        String email = authentication.getName();
        if(email != null){
            return new ResponseEntity<>(commentService.addComment(pubId, commText), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);

    }

    @PostMapping("/comment/delete")
    public ResponseEntity<String> deleteComment(Authentication authentication,
                                                @RequestParam int pubId , @RequestParam int commId){
        String email = authentication.getName();
        if(email != null){
            UserDto userDto = userService.getUserByEmail(email).get(0);
            return new ResponseEntity<>(commentService.deleteComment(Integer.parseInt(String.valueOf(userDto.getUserId())), pubId , commId), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);

    }
}
