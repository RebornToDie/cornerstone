package com.example.mq.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author reborntodie
 * @date 2019/11/1 11:17
 */
public class MqResult {

    public enum EXECUTE_STATE{
        NONE,
        SUCCESSFUL,
        FAILED
    }

    private MqData data;

    public MqData getData() {
        return data;
    }

    public void setData(MqData data) {
        this.data = data;
    }

    private String consumerName = "";

    private EXECUTE_STATE executeState = EXECUTE_STATE.NONE;

    private String error = "";

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public EXECUTE_STATE getExecuteState() {
        return executeState;
    }

    public void setExecuteState(EXECUTE_STATE executeState) {
        this.executeState = executeState;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSucceed() {
        return EXECUTE_STATE.SUCCESSFUL.equals(executeState);
    }

    public String getId() {
        MqData dataObj = getData();
        if (null == dataObj) {
            return null;
        } else {
            return dataObj.getId();
        }
    }

    public String getProducerName() {
        MqData dataObj = getData();
        if (null == dataObj) {
            return null;
        } else {
            return dataObj.getProducerName();
        }
    }


    private static final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

    public String toString() {
        return gson.toJson(this);
    }


}
