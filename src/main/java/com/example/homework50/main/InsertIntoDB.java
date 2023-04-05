package com.example.homework50.main;

import com.example.homework50.dao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class InsertIntoDB {
    private final UserDao userDao;
    private final PublicationDao publicationDao;
    private final SubscriptionsDao subscriptionsDao;
    private final LikeDao likeDao;
    private final CommentDao commentDao;
    private final JdbcTemplate jdbcTemplate;
    @Bean
    public void doInsert(){
        if(isEmptyBase()){
            inserts();
        }
    }

    private void inserts(){
        userDao.createUser("user1", "fff", "ff@mail.ru","rr" );
        userDao.createUser("user2", "ttt", "dd@mail.ru","2r" );
        userDao.createUser("user3", "err", "f444@mail.ru","tr" );

        publicationDao.createPublication("images/1.jpg",
                "hey",
                1,
                LocalDateTime.of(2000,2,23, 14,47));
        publicationDao.createPublication("images/2.jpg",
                "hello",
                1,
                LocalDateTime.of(2002,3,13, 10,17));
        publicationDao.createPublication("images/3.jpg",
                "like me",
                2,
                LocalDateTime.of(2001,2,15, 11,16));
        publicationDao.createPublication("images/4.jpg",
                "hey everyone",
                2,
                LocalDateTime.of(2001,3,16, 12,19));
        publicationDao.createPublication("images/5.jpg",
                "bad",
                2,
                LocalDateTime.of(2001,2,15, 11,16));
        publicationDao.createPublication("images/6.jpg",
                "good",
                3,
                LocalDateTime.of(2005,2,15, 11,16));

        subscriptionsDao.createSubscriptions(1L,
                2L,
                LocalDateTime.of(2004,6,18, 8,16));
        subscriptionsDao.createSubscriptions(1L,
                3L,
                LocalDateTime.of(2004,6,18, 8,20));
        likeDao.createLike(1L,3L,
                LocalDateTime.of(2004,6,18, 8,16));

        likeDao.createLike(1L,6L,
                LocalDateTime.of(2004,6,18, 8,25));
        commentDao.createComment(1,1,"hey",
                LocalDateTime.of(2004,6,18, 8,16));
        commentDao.createComment(2,2,"Hello",
                LocalDateTime.of(2004,6,18, 8,30));
    }

    private boolean isEmptyBase(){
        String sql = "select * from users";
            List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
            if(users.size() < 1){
                return true;
            }else {
                return false;
            }
    }
}
