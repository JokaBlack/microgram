package com.example.homework50.dao;

import com.example.homework50.dto.CommentDto;
import com.example.homework50.main.Comment;
import com.example.homework50.main.CommentRowMapper;
import com.example.homework50.main.Publication;
import com.example.homework50.main.PublicationRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;
    public void createComment(int userId,int pubId, String commText, LocalDateTime dateTime){
        String sql = "insert into comments (pub_id, comm_text,date_time, user_id) values(?,?,?,?);";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, pubId);
            ps.setString(2, commText);
            ps.setObject(3, dateTime);
            ps.setInt(4, userId);
            return ps;
        });
    }


    public int deleteComment(int userId, int pubId, int commId) {
        String sql = "delete from comments where comm_id = ? and pub_id = (select pub_id from publications where user_idfk = ? and pub_id = ?);";
        return jdbcTemplate.update(sql, commId, userId, pubId);
    }

    public List<Comment> getPublicationComments(int pubId) {
        String sql = "select * from comments as c inner join users as u on u.user_id = c.user_id where pub_id = " + pubId;
        return jdbcTemplate.query(sql, new CommentRowMapper());
    }

    public Comment getLastComment(int userId){
        String sql = "select * from comments as c " +
                "inner join users u on u.user_id = c.user_id " +
                "where c.comm_id = (select MAX(comm_id) from comments where user_id = " + userId + ");";
        return jdbcTemplate.queryForObject(sql, new CommentRowMapper());
    }
}
