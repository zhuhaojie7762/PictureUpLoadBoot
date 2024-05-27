package com.platform.entity;

import java.util.Objects;

/**
 * 返回对象公共部分
* @Author:zhuhaojie
* @Date:9:36 2018/12/12
*/
public class BaseBean {
    /**
     * 是否调用成功
     */
    private Boolean success;
    /**
     * 202	错误信息对应的代码，成功则为 null
     */
    private String errorCode;
    /**
     * invalid app key	错误信息，成功则为 null
     */
    private String errorMsg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseBean baseBean = (BaseBean) o;
        return success.equals(baseBean.success) &&
                errorCode.equals(baseBean.errorCode) &&
                errorMsg.equals(baseBean.errorMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, errorCode, errorMsg);
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
