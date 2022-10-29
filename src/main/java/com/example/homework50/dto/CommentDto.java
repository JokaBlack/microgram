package com.example.homework50.dto;

import com.example.homework50.main.Publication;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private Long commId;
    private PublicationDto publication;
    private String commText;
    private LocalDateTime dateTimeOfDescription;
    private UserDto userDto;
}
