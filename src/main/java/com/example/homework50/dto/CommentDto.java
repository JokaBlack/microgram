package com.example.homework50.dto;

import com.example.homework50.main.Publication;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long commId;
    private PublicationDto publication;
    private String commText;
    private LocalDateTime dateTimeOfDescription;
}
