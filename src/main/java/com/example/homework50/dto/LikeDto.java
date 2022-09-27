package com.example.homework50.dto;

import com.example.homework50.main.Publication;
import com.example.homework50.main.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LikeDto {
    private Long likeId;
    private UserDto whoLikes;
    private Publication likeOn;
    private LocalDateTime likedDateTime;
}
