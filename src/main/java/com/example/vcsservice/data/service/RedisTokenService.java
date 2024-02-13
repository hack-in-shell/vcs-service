package com.example.vcsservice.data.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTokenService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTokenService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }


}