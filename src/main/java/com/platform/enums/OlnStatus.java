package com.platform.enums;

import java.util.Objects;

/**
 * 线上状态枚举类
 *
 * @Author:zhuhaojie
 * @Date:15:37 2018/12/14
 */
public enum OlnStatus {

    UN_DEAL("未建交易", 0),
    WAIT_PAY("等待付款", 1),
    WAIT_SEND("等待发货(部分发货)", 2),
    FINISH("已完成", 3),
    CLOSED("已关闭", 4),
    WAIT_VERIFY("等待确认", 5),
    SIGN("已签收", 6);


    private String name;
    private Integer code;

    OlnStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }


    public Integer getCode(String name) {
        for (OlnStatus olnStatus : OlnStatus.values()) {
            if (olnStatus.getName().equals(name)) {
                return olnStatus.getCode();
            }
        }
        return null;
    }

    public String getName(Integer code) {
        for (OlnStatus olnStatus : OlnStatus.values()) {
            if (Objects.equals(olnStatus.getCode(), code)) {
                return olnStatus.getName();
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}