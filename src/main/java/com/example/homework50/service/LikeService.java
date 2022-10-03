package com.example.homework50.service;

import com.example.homework50.dao.LikeDao;
import com.example.homework50.main.Like;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LikeService {
    private LikeDao likeDao;

    public Boolean isLikedServ(Long pubId, Long userId){
        return likeDao.isLikedDao(pubId,userId);
    }


    public String like(Long userId, Long pubId) {
        if(!isLikedServ(pubId,userId)){
            try {
                likeDao.createLike(userId, pubId, LocalDateTime.now());
                return "You liked it";
            }catch (Exception e){
                return "Сheck the correctness of the entered data";
            }
        }
        return "Liked";
    }
}
