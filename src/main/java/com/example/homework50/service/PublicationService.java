package com.example.homework50.service;

import com.example.homework50.dao.PublicationDao;
import com.example.homework50.dao.UserDao;
import com.example.homework50.dto.PublicationDto;
import com.example.homework50.dto.UserDto;
import com.example.homework50.main.Publication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PublicationService {
    private PublicationDao publicationDao;

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

    public String deletePublication(int userId, int pubId) {
        int answer = publicationDao.deletePublication(userId, pubId);
        if(answer > 0){
            return "Publication deleted";
        }
        return "No such publication";
    }


    public String addPublication(int userId, MultipartFile file, String description) {
        try {
            String link = "images/" + file.getOriginalFilename();
            publicationDao.createPublication(link, description, userId, LocalDateTime.now());
            File path = new File("J:\\Attractor\\Homeworks\\homeWork50\\src\\main\\resources\\static\\images\\" + file.getOriginalFilename());
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
            return "Publication added";
        } catch (Exception e) {
           return "Сheck the correctness of the entered data";
        }
    }
}
