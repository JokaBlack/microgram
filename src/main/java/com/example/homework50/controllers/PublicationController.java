package com.example.homework50.controllers;

import com.example.homework50.dto.PublicationDto;
import com.example.homework50.service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
public class PublicationController {
    private PublicationService publicationService;
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
            @CookieValue(value = "userId", required = false, defaultValue = "0") String userId,
            @RequestParam int pubId){
        int id = Integer.parseInt(userId);
        if(!"0".equals(userId)){
            return new ResponseEntity<>(publicationService.deletePublication(id, pubId),HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }

    @PostMapping("/publications/add")
    public ResponseEntity<?> addPublication(
            @CookieValue(value = "userId", required = false, defaultValue = "0") String userId,
            @PathVariable MultipartFile file, @RequestParam String description){
        int id = Integer.parseInt(userId);
        if(!"0".equals(userId)){
            return new ResponseEntity<>(publicationService.addPublication(id, file, description),HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized", HttpStatus.OK);
    }


}
