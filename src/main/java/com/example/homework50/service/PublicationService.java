package com.example.homework50.service;

import com.example.homework50.dao.PublicationDao;
import com.example.homework50.dao.UserDao;
import com.example.homework50.dto.PublicationDto;
import com.example.homework50.dto.UserDto;
import com.example.homework50.main.Publication;
import com.example.homework50.main.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class PublicationService {
    private PublicationDao publicationDao;
    private UserService userService;

    private UserDao userDao;
    public List<PublicationDto> getUserPublications(Long userId) {
        return conversionToListDto(publicationDao.getUserPublications(userId));
    }

    public List<PublicationDto> createAFeedForAUser(Long userId) {
        return conversionToListDto(publicationDao.createAFeedForAUser(userId));
    }

    private List<PublicationDto> conversionToListDto(List<Publication> publications){
        List <PublicationDto> publicationDtos = new ArrayList<>();
        for (var e:publications) {
            UserDto dd = UserDto.builder()
                    .userId(e.getUser().getUserId())
                    .nickName(e.getUser().getNickName())
                    .email(e.getUser().getEmail())
                    .build();
            PublicationDto rr = PublicationDto.builder()
                    .pubId(e.getPubId())
                    .imgLink(e.getImgLink())
                    .description(e.getDescription())
                    .userDto(dd)
                    .dateTimeOfPublication(e.getDateTimeOfPublication())
                    .build();
            publicationDtos.add(rr);
        }
        return publicationDtos;
    }

}
