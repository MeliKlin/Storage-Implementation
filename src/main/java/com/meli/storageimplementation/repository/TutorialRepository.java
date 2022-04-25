package com.meli.storageimplementation.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.Map;

@Repository
@AllArgsConstructor
public class TutorialRepository {

    Jedis jedis;

    public void set(String key, String value) {
        jedis.hset("tutorials", key, value);
    }

    public Map<String, String> getAll() {
        return jedis.hgetAll("tutorials");
    }

    public String findById(String id) {
        return jedis.hget("tutorials", id);
    }

    public void delete(String key) {
        jedis.hdel("tutorials", key);
    }

}
