package com.example.homework50.controllers;

import com.example.homework50.dto.UserDto;
import com.example.homework50.main.User;
import com.example.homework50.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

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

    @PostMapping("/user/auth")
    public ResponseEntity<String> loginIn(@CookieValue(value = "userId", required = false, defaultValue = "0") String userId,
                                          @RequestParam String email, @RequestParam String password,
                                          HttpServletResponse response){
        if("0".equals(userId) && userService.isSuccessfulAuth(email,password)){
            Cookie cookie = new Cookie("userId", "" + userService.getUserByEmail(email).get(0).getUserId());
            cookie.setMaxAge(7000);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new ResponseEntity<>("Successful auth", HttpStatus.OK);
        } else if (!"0".equals(userId)) {
            return new ResponseEntity<>("You are logged in",HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong email address or password", HttpStatus.OK);
    }
}
