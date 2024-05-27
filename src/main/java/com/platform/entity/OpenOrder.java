package com.platform.entity;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.Objects;


/**
 * 订单明细
* @Author:zhuhaojie
* @Date:16:05 2018/12/14
*/
public class OpenOrder {


    private Integer id;

    private Integer userId;
    /**
     * 商品名称
     */
    @JSONField(name="item_name")
    private String itemName;
    /**
     * 线上商品id
     */
    @JSONField(name="oln_item_id")
    private String olnItemId;
    /**
     * 线上商品名称
     */
    @JSONField(name="oln_item_name")
    private String olnItemName;
    /**
     * 线上规格id
     */
    @JSONField(name="oln_sku_id")
    private String olnSkuId;
    /**
     * 线上状态:1:等待付款 2:等待发货 ,部分发货 3:已完成  4:已关闭 5: 等待确认 6:已签收 0: 未建交易
     */
    @JSONField(name="oln_status")
    private Integer olnStatus;
    /**
     * 明细id，单据级唯一
     */
    @JSONField(name="order_id")
    private String orderId;


    /**
     * 线上单号,如果是线下订单，则是万里牛的单号，合单情况下会将单号合并，使用|做分隔符
     * 根据tp_tid进行关联
     */
    @JSONField(name = "tp_tid")
    private String tpTid;

    /**
     * 销售金额
     */
    private Double payment;

    /**
     * 单价(商品标价)
     */
    private Double price;

    /**
     * 应收
     */
    private Double receivable;
    /**
     * 数量
     */
    private Integer size;
    /**
     * 商品编码(货号)
     */
    @JSONField(name="sku_code")
    private String skuCode;
    /**
     * 状态:1:等待付款 2:等待发货 ,部分发货 3:已完成  4:已关闭 5: 等待确认 6:已签收 0: 未建交易
     */
    private Integer status;

    /**
     * 记录创建时间
     */
    private Date createTime;
    /**
     * 记录修改时间
     */
    private Date updateTime;

    /**
     * 系统单号
     * trade_no和tp_tid 唯一确定一单
     */
    private String tradeNo;


    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReceivable() {
        return receivable;
    }

    public void setReceivable(Double receivable) {
        this.receivable = receivable;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getTpTid() {
        return tpTid;
    }

    public void setTpTid(String tpTid) {
        this.tpTid = tpTid;
    }

    public String getOlnItemId() {
        return olnItemId;
    }

    public void setOlnItemId(String olnItemId) {
        this.olnItemId = olnItemId;
    }

    public Integer getOlnStatus() {
        return olnStatus;
    }

    public void setOlnStatus(Integer olnStatus) {
        this.olnStatus = olnStatus;
    }

    public String getOlnSkuId() {
        return olnSkuId;
    }

    public void setOlnSkuId(String olnSkuId) {
        this.olnSkuId = olnSkuId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOlnItemName() {
        return olnItemName;
    }

    public void setOlnItemName(String olnItemName) {
        this.olnItemName = olnItemName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpenOrder openOrder = (OpenOrder) o;
        return Objects.equals(id, openOrder.id) &&
                Objects.equals(userId, openOrder.userId) &&
                Objects.equals(itemName, openOrder.itemName) &&
                Objects.equals(olnItemId, openOrder.olnItemId) &&
                Objects.equals(olnItemName, openOrder.olnItemName) &&
                Objects.equals(olnSkuId, openOrder.olnSkuId) &&
                Objects.equals(olnStatus, openOrder.olnStatus) &&
                Objects.equals(orderId, openOrder.orderId) &&
                Objects.equals(tpTid, openOrder.tpTid) &&
                Objects.equals(payment, openOrder.payment) &&
                Objects.equals(price, openOrder.price) &&
                Objects.equals(receivable, openOrder.receivable) &&
                Objects.equals(size, openOrder.size) &&
                Objects.equals(skuCode, openOrder.skuCode) &&
                Objects.equals(status, openOrder.status) &&
                Objects.equals(createTime, openOrder.createTime) &&
                Objects.equals(updateTime, openOrder.updateTime) &&
                Objects.equals(tradeNo, openOrder.tradeNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, itemName, olnItemId, olnItemName, olnSkuId, olnStatus, orderId, tpTid, payment, price, receivable, size, skuCode, status, createTime, updateTime, tradeNo);
    }

    @Override
    public String toString() {
        return "OpenOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemName='" + itemName + '\'' +
                ", olnItemId='" + olnItemId + '\'' +
                ", olnItemName='" + olnItemName + '\'' +
                ", olnSkuId='" + olnSkuId + '\'' +
                ", olnStatus=" + olnStatus +
                ", orderId='" + orderId + '\'' +
                ", tpTid='" + tpTid + '\'' +
                ", payment=" + payment +
                ", price=" + price +
                ", receivable=" + receivable +
                ", size=" + size +
                ", skuCode='" + skuCode + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tradeNo='" + tradeNo + '\'' +
                '}';
    }
}
