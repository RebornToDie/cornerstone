package com.example.mq.core.lock;

import java.util.concurrent.TimeUnit;

/**
 * distributed lock interface
 * @author reborntodie
 * @date 2019/11/1 14:00
 */
public interface Locker {

    /**
     * add lock
     * @param key
     * @param lockExpireTime 设置超时最大时间，预防死锁
     * @param timeUnit
     * @return isSuccessed
     */
    public boolean tryLock(String key, long lockExpireTime, TimeUnit timeUnit);

    /**
     * @param key
     * @return
     */
    public boolean isLock(String key);

    /**
     * release lock
     * @param key
     */
    public void unLock(String key);


}
