package com.example.homework50.main;

import com.example.homework50.dto.PublicationDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long commId;
    private Publication publication;
    private String commText;
    private LocalDateTime dateTimeOfDescription;
}
