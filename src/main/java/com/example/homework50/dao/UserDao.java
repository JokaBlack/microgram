package com.example.homework50.dao;

import com.example.homework50.main.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getUsersByName(String name) {
        String sql = "select * from users where nick_name LIKE " + "'" + name + "%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersByLogin(String login) {
        String sql = "select * from users where login LIKE " + "'" + login + "%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserByEmail(String email) {
        String sql = "select * from users where email = ?;";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
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
}
