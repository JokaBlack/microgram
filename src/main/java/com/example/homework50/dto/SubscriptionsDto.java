package com.example.homework50.dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionsDto {
    private Long subId;
    private UserDto whoSubscribes;
    private UserDto whoIsSubscribedTo;
    private LocalDateTime subDateTime;
}
