package com.example.mq.redis.client;

import redis.clients.jedis.commands.BasicCommands;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.commands.MultiKeyCommands;

/**
 * @author reborntodie
 * @date 2019/11/1 15:56
 */
public interface IJedis extends JedisCommands, MultiKeyCommands, BasicCommands {
}
