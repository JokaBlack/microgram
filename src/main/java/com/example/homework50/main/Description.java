package com.example.homework50.main;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Description {
    private Long descId;
    private String descText;
    private LocalDateTime dateTimeOfDescription;
}
