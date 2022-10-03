package com.example.homework50.service;

import com.example.homework50.dao.CommentDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentDao commentDao;

    public String addComment(int pubId, String commText) {
        try{
            commentDao.createComment(pubId, commText, LocalDateTime.now());
            return "Your comment has been published";
        }catch (Exception e){
            return "Сheck the correctness of the entered data";
        }
    }

    public String deleteComment(int userId, int pubId, int commId) {
            int answer = commentDao.deleteComment(userId, pubId, commId);
            if (answer > 0){
                return "Your comment has been removed";
            }
            return "Сheck the correctness of the entered data";

    }
}
