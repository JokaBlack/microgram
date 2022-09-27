package com.example.homework50.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PublicationDto {
    private Long pubId;
    private String imgLink;
    private String description;
    private UserDto userDto;
    private LocalDateTime dateTimeOfPublication;
}
