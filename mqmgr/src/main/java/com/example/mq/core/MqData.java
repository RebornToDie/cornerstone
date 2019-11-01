package com.example.mq.core;

/**
 * @author reborntodie
 * @date 2019/11/1 11:13
 */
public class MqData {

    private String id = "";

    private String data = "";

    private String producerName = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }


    public String toString() {
        return String.format(" id:%s, data:%s, producerName:%s", getId(), getData(), getProducerName());
    }


}
