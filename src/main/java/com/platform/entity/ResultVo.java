package com.platform.entity;

import java.util.List;
import java.util.Objects;

/**
 * 公共返回对象
* @Author:zhuhaojie
* @Date:9:32 2018/12/12
*/
public class ResultVo<T> {
    /**
     * 执行结果
     * true:成功
     * false:失败
     */
    private boolean result;
    /**
     *执行后消息
     */
    private String message;
    /**
     * 执行后数据
     */
    private List<T> data;

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultVo<?> resultVo = (ResultVo<?>) o;
        return result == resultVo.result &&
                Objects.equals(message, resultVo.message) &&
                Objects.equals(data, resultVo.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, message, data);
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
