package com.example.homework50.main;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionsPerUser {
    private Long subId;
    private User whoSubscribes;
    private User whoIsSubscribedTo;
    private LocalDateTime subDateTime;
}
