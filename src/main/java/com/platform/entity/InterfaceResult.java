package com.platform.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 接口返回值
* @Author:zhuhaojie
* @Date:12:40 2018/12/18
*/
public class InterfaceResult implements Serializable {
    /**
     * 编码
     */
    private String code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private List<OrderStatusResult> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderStatusResult> getData() {
        return data;
    }

    public void setData(List<OrderStatusResult> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "InterfaceResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
