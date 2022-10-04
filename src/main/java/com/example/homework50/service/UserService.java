package com.example.homework50.service;

import com.example.homework50.dao.UserDao;
import com.example.homework50.dto.UserDto;
import com.example.homework50.main.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private  UserDao userDao;

    public List<UserDto> getUsersByName(String name){
        return conversionToListDto(userDao.getUsersByName(name));
    }
    public List<UserDto> getUsersByLogin(String login){
        return conversionToListDto(userDao.getUsersByLogin(login));
    }
    public List<UserDto> getUserByEmail(String email){
        return conversionToListDto(userDao.getUserByEmail(email));
    }

    public boolean isContains(String email) {
        return userDao.isContains(email);
    }

    public List<UserDto> conversionToListDto(List<User> users){
        List <UserDto> userDtos = new ArrayList<>();
        for (var e:users) {
            UserDto rr = UserDto.builder()
                    .userId(e.getUserId())
                    .nickName(e.getNickName())
                    .email(e.getEmail())
                    .build();
            userDtos.add(rr);
        }
        return userDtos;
    }

    public String register(String nickName, String login, String email, String password) {
        if(!userDao.isContains(email) && !userDao.isContainsByLogin(login)){
            userDao.createUser(nickName,login,email,password);
            return "Successful registration";
        }else {
            return "try using a different email or username";
        }
    }

}
