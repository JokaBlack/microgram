package com.example.homework50.main;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Like {
    private Long likeId;
    private User whoLikes;
    private Publication likeOn;
    private LocalDateTime likedDateTime;
}
