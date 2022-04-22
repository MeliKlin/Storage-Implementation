package com.meli.storageimplementation.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
@AllArgsConstructor
public class TutorialRepository {

    Jedis jedis;

    public void set(String key, String value) {
        jedis.hset("tutorials", key, value);
    }

}
