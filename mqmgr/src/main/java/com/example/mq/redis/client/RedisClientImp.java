package com.example.mq.redis.client;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author reborntodie
 * @date 2019/11/7 16:52
 */
public class RedisClientImp implements IRedisClient {
    @Override
    public void init(String server, int db, String pwd, JedisPoolConfig config) {

    }

    @Override
    public void destory() {

    }

    @Override
    public IJedis getClient() {
        return null;
    }

    @Override
    public IJedis getClient(int db) {
        return null;
    }

    @Override
    public void returnClient(IJedis jedis) {

    }

    @Override
    public void returnBrokenResource(IJedis jedis) {

    }
}
