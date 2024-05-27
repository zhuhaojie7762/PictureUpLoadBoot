package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 订单原始内容表
* @Author:zhuhaojie
* @Date:11:58 2018/12/19
*/
public class OrderOrigin implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户权限id
     */
    private Integer userId;
    /**
     * 请求开始时间
     */
    private Date beginTime;
    /**
     * 请求结束时间
     */
    private Date endTime;
    /**
     * 内容
     */
    private String content;
    /**
     * 记录更新时间
     */
    private Date updateTime;
    /**
     * 记录创建时间
     */
    private Date createTime;


    /**
     *查询用字段
     * @return
     */
    private Date beginTimeStart;
    private Date beginTimeEnd;
    private Date endTimeStart;
    private Date endTimeEnd;


    public Date getBeginTimeStart() {
        return beginTimeStart;
    }

    public void setBeginTimeStart(Date beginTimeStart) {
        this.beginTimeStart = beginTimeStart;
    }

    public Date getBeginTimeEnd() {
        return beginTimeEnd;
    }

    public void setBeginTimeEnd(Date beginTimeEnd) {
        this.beginTimeEnd = beginTimeEnd;
    }

    public Date getEndTimeStart() {
        return endTimeStart;
    }

    public void setEndTimeStart(Date endTimeStart) {
        this.endTimeStart = endTimeStart;
    }

    public Date getEndTimeEnd() {
        return endTimeEnd;
    }

    public void setEndTimeEnd(Date endTimeEnd) {
        this.endTimeEnd = endTimeEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderOrigin{" +
                "id=" + id +
                ", userId=" + userId +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", content='" + content + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", beginTimeStart=" + beginTimeStart +
                ", beginTimeEnd=" + beginTimeEnd +
                ", endTimeStart=" + endTimeStart +
                ", endTimeEnd=" + endTimeEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderOrigin that = (OrderOrigin) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(beginTime, that.beginTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(content, that.content) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(beginTimeStart, that.beginTimeStart) &&
                Objects.equals(beginTimeEnd, that.beginTimeEnd) &&
                Objects.equals(endTimeStart, that.endTimeStart) &&
                Objects.equals(endTimeEnd, that.endTimeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, beginTime, endTime, content, updateTime, createTime, beginTimeStart, beginTimeEnd, endTimeStart, endTimeEnd);
    }
}
