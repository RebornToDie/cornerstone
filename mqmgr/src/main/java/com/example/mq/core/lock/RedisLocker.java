package com.example.mq.core.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author reborntodie
 * @date 2019/11/1 14:09
 */
public class RedisLocker implements Locker {
    @Override
    public boolean tryLock(String key, long lockExpireTime, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean isLock(String key) {
        return false;
    }

    @Override
    public void unLock(String key) {

    }
}
