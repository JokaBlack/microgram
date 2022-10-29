package com.example.homework50.controllers;

import com.example.homework50.dto.UserDto;
import com.example.homework50.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private  UserService userService;
    @GetMapping("/searchbyname/{name}")
    public ResponseEntity<List<UserDto>> usersByNameEntity(@PathVariable String name){
        return new ResponseEntity<>(userService.getUsersByName(name), HttpStatus.OK);
    }

    @GetMapping("/searchbylogin/{login}")
    public ResponseEntity<List<UserDto>> usersByLoginEntity(@PathVariable String login){
        return new ResponseEntity<>(userService.getUsersByLogin(login), HttpStatus.OK);
    }
    @GetMapping("/searchbyemail/{email}")
    public ResponseEntity<List<UserDto>> userByEmailEntity(@PathVariable String email){
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/contains/{email}")
    public ResponseEntity<Boolean> userContains(@PathVariable String email){
        return new ResponseEntity<>(userService.isContains(email), HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestParam String nickName, @RequestParam String login,
                                           @RequestParam String email, @RequestParam String password){
        return new ResponseEntity<>(userService.register(nickName, login, email, password), HttpStatus.OK);
    }


}
