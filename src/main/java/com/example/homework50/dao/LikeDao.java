package com.example.homework50.dao;

import com.example.homework50.main.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LikeDao {
    private final JdbcTemplate jdbcTemplate;
    public Boolean isLikedDao(Long pubId, Long userId) {
        String sql = "select * from likes where pub_id = ? and user_id  = ?;";
        try {
            Like like = jdbcTemplate.queryForObject(sql
                    , new BeanPropertyRowMapper<>(Like.class), pubId, userId);
            return like != null;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public void createLike(Long userId, Long pubId, LocalDateTime dateTime) {
        String sql = "insert into likes(user_id, pub_id, date_time) values(?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, userId);
            ps.setLong(2, pubId);
            ps.setObject(3, dateTime);
            return ps;
        }, keyHolder);
    }
}
