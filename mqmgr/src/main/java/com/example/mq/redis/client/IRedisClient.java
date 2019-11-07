package com.example.mq.redis.client;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author reborntodie
 * @date 2019/11/7 16:33
 */
public interface IRedisClient {

    /**
     * 初始化链接对象
     * @param server
     * @param db
     * @param pwd
     * @param config
     */
    public void init(String server, int db, String pwd, JedisPoolConfig config);

    /**
     * 销毁对象
     */
    public void destory();

    public IJedis getClient();

    public IJedis getClient(int db);

    public void returnClient(IJedis jedis);

    public void returnBrokenResource(IJedis jedis);
}
