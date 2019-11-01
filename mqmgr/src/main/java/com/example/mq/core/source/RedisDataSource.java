package com.example.mq.core.source;

import com.example.mq.core.TaskDataSource;

/**
 * @author reborntodie
 * @date 2019/11/1 14:55
 */
public class RedisDataSource extends TaskDataSource {

    public RedisDataSource(){
        this.setType("redis");
    }

}
