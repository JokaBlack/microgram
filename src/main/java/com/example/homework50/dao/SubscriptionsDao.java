package com.example.homework50.dao;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SubscriptionsDao {
    private final JdbcTemplate jdbcTemplate;

    public void createSubscriptions(Long whoSubscribes, Long whoIsSubscribedTo, LocalDateTime subDateTime) {
        String sql = "insert into subscriptions (who_subscribes_id, who_is_subscribed_to_id, date_time) values(?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, whoSubscribes);
            ps.setLong(2, whoIsSubscribedTo);
            ps.setObject(3, subDateTime);
            return ps;
        }, keyHolder);
    }
}
