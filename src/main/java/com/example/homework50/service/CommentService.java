package com.example.homework50.service;

import com.example.homework50.dao.CommentDao;
import com.example.homework50.dto.CommentDto;
import com.example.homework50.dto.UserDto;
import com.example.homework50.main.Comment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentDao commentDao;

//    public String addComment(int pubId, String commText) {
//        try{
//            commentDao.createComment(pubId, commText, LocalDateTime.now());
//            return "Your comment has been published";
//        }catch (Exception e){
//            return "Сheck the correctness of the entered data";
//        }
//    }

    public ResponseEntity<?> addComment(int userId, int pubId, String commText) {
        try{
            commentDao.createComment(userId, pubId, commText, LocalDateTime.now());

            Comment lastComment = commentDao.getLastComment(userId);

            UserDto userDto = UserDto.builder()
                    .userId(lastComment.getUser().getUserId())
                    .nickName(lastComment.getUser().getNickName())
                    .email(lastComment.getUser().getEmail())
                    .build();

            CommentDto commentDto = CommentDto.builder()
                    .commId(lastComment.getCommId())
                    .commText(lastComment.getCommText())
                    .dateTimeOfDescription(lastComment.getDateTimeOfDescription())
                    .userDto(userDto)
                    .publication(null)
                    .build();
            return new ResponseEntity<>(commentDto, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public String deleteComment(int userId, int pubId, int commId) {
            int answer = commentDao.deleteComment(userId, pubId, commId);
            if (answer > 0){
                return "Your comment has been removed";
            }
            return "Сheck the correctness of the entered data";

    }

    public List<CommentDto> getCommentsDto(int pubId) {
        List<CommentDto> commentDtos = new ArrayList<>();
        List<Comment> comments = commentDao.getPublicationComments(pubId);
        for (var e:comments) {
            UserDto user = UserDto.builder()
                    .userId(e.getUser().getUserId())
                    .nickName(e.getUser().getNickName())
                    .email(e.getUser().getEmail())
                    .build();
            CommentDto commentDto = CommentDto.builder()
                    .commId(e.getCommId())
                    .commText(e.getCommText())
                    .dateTimeOfDescription(e.getDateTimeOfDescription())
                    .publication(null)
                    .userDto(user)
                    .build();
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }
}
