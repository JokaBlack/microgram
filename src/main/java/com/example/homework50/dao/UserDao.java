package com.example.homework50.dao;

import com.example.homework50.main.Publication;
import com.example.homework50.main.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUsersByName(String name) {
        String sql = "select * from users where nick_name LIKE " + "'" + name + "%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersByLogin(String login) {
        String sql = "select * from users where login LIKE " + "'" + login + "%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUserByEmail(String email) {
        String sql = "select * from users where email = ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email);
    }
    public List<User> getUserById(Long id) {
        String sql = "select * from users where user_id = ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public boolean isContains(String email) {
        String sql = "select * from users where email = ?;";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
            return user != null;
        }catch (EmptyResultDataAccessException ex){
            return false;
        }
    }

    public boolean isContainsByLogin(String login) {
        String sql = "select * from users where login = ?;";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), login);
            return user != null;
        }catch (EmptyResultDataAccessException ex){
            return false;
        }
    }

    public void createUser(String nickname, String login, String email, String password) {
        String sql = "insert into users (nick_name,login,email,password) values(?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        addAuth(email);
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, nickname);
            ps.setString(2, login);
            ps.setString(3, email);
            ps.setString(4, passwordEncoder.encode(password));
            return ps;
        }, keyHolder);
    }

    public void addAuth(String email){
        String sql = "insert into authorities (username,authority) values(?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, email);
            ps.setString(2, "ROLE_USER");
            return ps;
        }, keyHolder);
    }

    public boolean passCheck(String email, String password) {
        String sql = "select password from users where email = ?;";
        String encodedPass = jdbcTemplate.queryForObject(sql, new SingleColumnRowMapper<>(), email);
        return passwordEncoder.matches(password, encodedPass);
    }
}
