package com.meli.storageimplementation.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class JedisClient {

    @Bean
    public Jedis getConnection() {
        return new JedisPool("localhost", 7000).getResource();
    }

}
