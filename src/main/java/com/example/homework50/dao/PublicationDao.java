package com.example.homework50.dao;

import com.example.homework50.main.Publication;
import com.example.homework50.main.PublicationRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PublicationDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Publication> getUserPublications(Long userId) {
        String sql = "select * from publications as p inner join users u on u.user_id = p.user_idfk where p.user_idfk = " + userId;
        return jdbcTemplate.query(sql, new PublicationRowMapper());
    }

    public List<Publication> createAFeedForAUser(Long userId){
        String sql = "SELECT * FROM publications as p inner join users u on u.user_id = p.user_idfk" +
                "        where p.user_idfk in" +
                "                (select who_is_subscribed_to_id from subscriptions where who_subscribes_id = " + userId + ")";
        return jdbcTemplate.query(sql, new PublicationRowMapper());
    }

    public void createPublication(String link, String description, int userId, LocalDateTime dateTime){
        String sql = "insert into publications (img_link, description, user_idfk,date_time) values(?,?,?,?);";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, link);
            ps.setString(2, description);
            ps.setInt(3, userId);
            ps.setObject(4, dateTime);
            return ps;
        });
    }

    public int deletePublication(int userId, int pubId) {
        String sql = "delete   from publications where pub_id = ?  and user_idfk = ?;";
        return jdbcTemplate.update(sql,pubId, userId);
    }

    public Publication getLastPublication(int userId){
        String sql = "select * from publications as p " +
                "inner join users u on u.user_id = p.user_idfk " +
                "where p.pub_id = (select MAX(pub_id) from publications where user_idfk = " + userId + ");";
        return jdbcTemplate.queryForObject(sql, new PublicationRowMapper());
    }
}
