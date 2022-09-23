package com.example.homework50.main;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String nickName;
    private String login;
    private String email;
    private String password;
    private int publicationCounter;
    private int subscriptionsCounter;
    private int subscribersCounter;
}
