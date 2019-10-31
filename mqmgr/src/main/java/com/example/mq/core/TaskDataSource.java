package com.example.mq.core;

/**
 * @author reborntodie
 * @date 2019/10/31 16:21
 */
public class TaskDataSource implements Cloneable {

    private String consumerName = "";

    private String name = "";

    private String type = "";

    private boolean processingLock = true;            // 发布消息是否加锁

    private boolean broadcastModel = false; //广播模式 广播模式下所有消费者均会收到发布者广播的消息

    private String server = "";

    private String password = "";

    private int db = 0;

    public boolean isBroadcastModel() {
        return broadcastModel;
    }

    public void setBroadcastModel(boolean broadcastModel) {
        this.broadcastModel = broadcastModel;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getProcessingLock() {
        return processingLock;
    }

    public void setProcessingLock(boolean processingLock) {
        this.processingLock = processingLock;
    }

    public TaskDataSource clone() {
        TaskDataSource o = null;
        try {
            o = (TaskDataSource) super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return o;
    }

}
