package com.example.homework50.controllers;

import com.example.homework50.dto.PublicationDto;
import com.example.homework50.dto.UserDto;
import com.example.homework50.main.User;
import com.example.homework50.service.PublicationService;
import com.example.homework50.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
public class PublicationController {
    private PublicationService publicationService;
    private UserService userService;

    @GetMapping("/publications/{userId}")
    public ResponseEntity<List<PublicationDto>> getUserPublicationsEntity(@PathVariable Long userId){
        return new ResponseEntity<>(publicationService.getUserPublications(userId), HttpStatus.OK);
    }

    @GetMapping("/feed/{userId}")
    public ResponseEntity<List<PublicationDto>> getUserFeed(@PathVariable Long userId){
        return new ResponseEntity<>(publicationService.createAFeedForAUser(userId), HttpStatus.OK);
    }

    @PostMapping("/publications/delete")
    public ResponseEntity<String> deletePublication(
            Authentication authentication,
            @RequestParam int pubId){
        String email = authentication.getName();
        if(email != null){
            UserDto userDto = userService.getUserByEmail(email).get(0);

            return new ResponseEntity<>(publicationService.deletePublication(Integer.parseInt(String.valueOf(userDto.getUserId())), pubId),HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }

//    @PostMapping("/publications/add")
//    public ResponseEntity<?> addPublication(
//            Authentication authentication,
//            @PathVariable MultipartFile file, @RequestParam String description){
//        String email = authentication.getName();
//
//        if(email != null){
//            UserDto userDto = userService.getUserByEmail(email).get(0);
//            return new ResponseEntity<>(publicationService.addPublication(Integer.parseInt(String.valueOf(userDto.getUserId())), file, description),HttpStatus.OK);
//        }
//        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
//    }
    @CrossOrigin
    @PostMapping("/publications/add")
    public ResponseEntity<?> addPublication(@RequestParam MultipartFile image,@RequestParam  String description, @RequestParam int userId ){
        return new ResponseEntity<>(publicationService.addPublication(image, description, userId), HttpStatus.OK);
    }


}
