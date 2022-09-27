package com.example.homework50.controllers;

import com.example.homework50.dto.PublicationDto;
import com.example.homework50.main.Publication;
import com.example.homework50.service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
