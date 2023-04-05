package com.example.homework50.main;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comm = new Comment();
        comm.setCommId(rs.getLong("comm_id"));
        comm.setCommText(rs.getString("comm_text"));
        comm.setDateTimeOfDescription(rs.getTimestamp("date_time").toLocalDateTime());

        comm.setPublication(null);

        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setNickName(rs.getString("nick_name"));
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        comm.setUser(user);
        return comm;
    }
}
