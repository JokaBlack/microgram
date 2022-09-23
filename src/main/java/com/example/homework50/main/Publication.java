package com.example.homework50.main;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Publication {
    private Long pubId;
    private String imgLink;
    private String description;
    private LocalDateTime dateTimeOfPublication;
}
