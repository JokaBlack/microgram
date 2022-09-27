package com.example.homework50.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;
    public void createComment(int userId, int pubId, LocalDateTime dateTime){
        String sql = "insert into comments (pub_id, comm_text,date_time) values(?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, userId);
            ps.setInt(2, pubId);
            ps.setObject(3, dateTime);
            return ps;
        }, keyHolder);
    }
}
