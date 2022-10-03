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
    public void createComment(int pubId, String commText, LocalDateTime dateTime){
        String sql = "insert into comments (pub_id, comm_text,date_time) values(?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, pubId);
            ps.setString(2, commText);
            ps.setObject(3, dateTime);
            return ps;
        }, keyHolder);
    }


    public int deleteComment(int userId, int pubId, int commId) {
        String sql = "delete from comments where comm_id = ? and pub_id = (select pub_id from publications where user_idfk = ? and pub_id = ?);";
        return jdbcTemplate.update(sql, commId, userId, pubId);
    }
}
