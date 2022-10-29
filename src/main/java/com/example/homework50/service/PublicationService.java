package com.example.homework50.service;

import com.example.homework50.dao.PublicationDao;
import com.example.homework50.dto.PublicationDto;
import com.example.homework50.dto.UserDto;
import com.example.homework50.main.Publication;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


//    public String addPublication(int userId, MultipartFile file, String description) {
//        try {
//            String link = "images/" + file.getOriginalFilename();
//            publicationDao.createPublication(link, description, userId, LocalDateTime.now());
//            File path = new File("J:\\Attractor\\Homeworks\\homeWork50\\src\\main\\resources\\static\\images\\" + file.getOriginalFilename());
//            path.createNewFile();
//            FileOutputStream output = new FileOutputStream(path);
//            output.write(file.getBytes());
//            output.close();
//            return "Publication added";
//        } catch (Exception e) {
//           return "Сheck the correctness of the entered data";
//        }
//    }

    public ResponseEntity<?> addPublication(MultipartFile file, String description, int userId) {
        try {
            String link = "images/" + file.getOriginalFilename();
            publicationDao.createPublication(link, description, userId, LocalDateTime.now());
            File path = new File("J:\\Attractor\\Homeworks\\homeWork50\\src\\main\\resources\\static\\images\\" + file.getOriginalFilename());
            path.createNewFile();

            FileOutputStream output = new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();

            Publication addedPublication = publicationDao.getLastPublication(userId);

            UserDto userDto = UserDto.builder()
                    .userId(addedPublication.getUser().getUserId())
                    .nickName(addedPublication.getUser().getNickName())
                    .email(addedPublication.getUser().getEmail())
                    .build();
            PublicationDto publicationDto = PublicationDto.builder()
                    .pubId(addedPublication.getPubId())
                    .imgLink(addedPublication.getImgLink())
                    .description(addedPublication.getDescription())
                    .userDto(userDto)
                    .dateTimeOfPublication(addedPublication.getDateTimeOfPublication())
                    .build();

            return new ResponseEntity<>(publicationDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
