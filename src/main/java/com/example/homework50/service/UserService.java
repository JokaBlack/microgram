package com.example.homework50.service;

import com.example.homework50.dao.UserDao;
import com.example.homework50.main.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private  UserDao userDao;

    public List<User> getUsersByName(String name){
        return userDao.getUsersByName(name);
    }
    public List<User> getUsersByLogin(String login){
        return userDao.getUsersByLogin(login);
    }
    public User getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    public boolean isContains(String email) {
        return userDao.isContains(email);
    }
}
