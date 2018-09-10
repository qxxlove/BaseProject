package com.example.dell.baseproject.bean;

import java.io.Serializable;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/23.
 * 邮箱：123123@163.com
 */

public class BaseModel  implements Serializable {

    private boolean executeResult;
    private String messageCode;
    private String data ;

    public boolean isExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(boolean executeResult) {
        this.executeResult = executeResult;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
