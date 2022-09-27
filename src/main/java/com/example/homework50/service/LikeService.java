package com.example.homework50.service;

import com.example.homework50.dao.LikeDao;
import com.example.homework50.main.Like;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeService {
    private LikeDao likeDao;

    public Boolean isLikedServ(Long pubId, Long userId){
        return likeDao.isLikedDao(pubId,userId);
    }


}
