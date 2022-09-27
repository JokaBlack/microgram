package com.example.homework50.main;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicationRowMapper implements RowMapper<Publication> {
    @Override
    public Publication mapRow(ResultSet rs, int rowNum) throws SQLException {
        Publication publication = new Publication();
        publication.setPubId(rs.getLong("pub_id"));
        publication.setImgLink(rs.getString("img_link"));
        publication.setDescription(rs.getString("description"));
        publication.setDateTimeOfPublication(rs.getTimestamp("date_time").toLocalDateTime());
        User user = new User();
        user.setUserId(rs.getLong("user_idfk"));
        user.setNickName(rs.getString("nick_name"));
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        publication.setUser(user);
        return publication;
    }
}
