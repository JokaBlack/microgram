package com.example.homework50.service;

import com.example.homework50.dao.SubscriptionsDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SubscribeService {
private SubscriptionsDao subscriptionsDao;
    public String addSubscription(Long userId, Long subTo) {
        try {
            subscriptionsDao.createSubscriptions(userId, subTo, LocalDateTime.now());
            return "You subscribed";
        }catch (Exception e){
            return "Сheck the correctness of the entered data";
        }
    }
}
