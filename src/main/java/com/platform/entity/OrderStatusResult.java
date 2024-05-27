package com.platform.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.platform.enums.OlnStatus;
import com.platform.enums.StatusEnum;
import com.platform.utils.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * 订单状态查询返回对象
 * billCode=XD181211000298&page=1&limit=20
 *
 * @Author:zhuhaojie
 * @Date:9:45 2018/12/12
 */
public class OrderStatusResult {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户权限值
     */
    private Integer userId;

    /**
     * 地址
     */
    private String address;
    /**
     * 买家昵称
     */
    private String buyer;
    /**
     * 旺旺等账号
     */
    @JSONField(name = "buyer_account")
    private String buyerAccount;
    /**
     * 买家留言
     */
    @JSONField(name = "buyer_msg")
    private String buyerMsg;
    /**
     * 市
     */
    private String city;
    /**
     * 创建时间
     */
    @JSONField(name = "create_time")
    private Date createTime;

    /**
     * 优惠金额
     */
    @JSONField(name = "discount_fee")
    private Double discountFee;
    /**
     * 区
     */
    private String district;

    /**
     * 快递单号
     */
    @JSONField(name = "express_code")
    private String expressCode;
    /**
     * 是否有退款
     */
    @JSONField(name = "has_refund")
    private Integer hasRefund;
    /**
     * 身份信息
     */
    @JSONField(name = "identity_num")
    private String identityNum;
    /**
     * 发票
     */
    private Invoice invoice;

    /**
     * 是否异常订单
     */
    @JSONField(name = "is_exception_trade")
    private Boolean exceptionTrade;
    /**
     * 是否已付款
     */
    @JSONField(name = "is_pay")
    private Boolean pay;
    /**
     * 是否jit小单
     */
    @JSONField(name = "is_small_trade")
    private Boolean smallTrade;
    /**
     * 万里牛ERP快递公司代码，用户自定义代码
     */
    @JSONField(name = "logistic_code")
    private String logisticCode;
    /**
     * 修改时间
     */
    @JSONField(name = "modify_time")
    private Date modifyTime;
    /**
     * 明细中线上单号集合
     */
    @JSONField(name = "oln_order_list")
    private List<String> olnOrderList;
    /**
     * 明细中线上单号集合
     */
    private String olnOrderStr;

    //private String olnOrderList;
    /**
     * 线上状态:1:等待付款 2:等待发货 ,部分发货 3:已完成  4:已关闭 5: 等待确认 6:已签收 0: 未建交易
     */
    @JSONField(name = "oln_status")
    private OlnStatus olnStatus;
    /**
     * 明细集合
     */
    private List<OpenOrder> orders;
    /**
     * 实际支付金额
     */
    @JSONField(name = "paid_fee")
    private Double paidFee;
    /**
     * 付款时间
     */
    @JSONField(name = "pay_time")
    private Date payTime;

    /**
     * 手机号，
     * 手机号为空的时候返回电话
     */
    private String phone;

    /**
     * 邮费
     */
    @JSONField(name = "post_fee")
    private Double postFee;
    /**
     * 万里牛单据处理状态: -3:分销商审核  -2:到账管理  -1:未付款
     * 0:审核  1:打单配货  2:验货   3:称重   4:待发货  5：财审
     * 8:已发货   9:成功  10:关闭  11:异常结束  12:异常处理
     * 13:外部系统配货中   14:预售  15:打包
     */
    @JSONField(name = "process_status")
    private Integer processStatus;
    /**
     * 省
     */
    private String province;
    /**
     * 收件人
     */
    private String receiver;
    /**
     * 备注
     */
    private String remark;
    /**
     * 卖家留言
     */
    @JSONField(name = "seller_msg")
    private String sellerMsg;

    /**
     * 发货时间,创建时间不为空时使用
     */
    @JSONField(name = "send_time")
    private Date sendTime;

    /**
     * 服务费
     */
    @JSONField(name = "service_fee")
    private Double serviceFee;

    /**
     * 店铺名称(页面上显示)
     */
    @JSONField(name = "shop_name")
    private String shopName;

    /**
     * 店铺昵称(店铺唯一)
     */
    @JSONField(name = "shop_nick")
    private String shopNick;


    /**
     * 订单来源
     */
    @JSONField(name = "source_platform")
    private String sourcePlatform;
    /**
     * 状态
     * 1：处理中 2：发货 3：完成  4: 关闭 5:其他
     */
    private StatusEnum status;

    /**
     * 仓库编码
     */
    @JSONField(name = "storage_code")
    private String storageCode;

    /**
     * 仓库名称
     */
    @JSONField(name = "storage_name")
    private String storageName;

    /**
     * 总金额,包含优惠
     */
    @JSONField(name = "sum_sale")
    private Double sumSale;
    /**
     * 电话
     */
    private String tel;
    /**
     * 线上单号,如果是线下订单，则是万里牛的单号，合单情况下会将单号合并，使用|做分隔符
     *
     * 订单号
     */
    @JSONField(name = "tp_tid")
    private String tpTid;

    /**
     * 订单编码 唯一的
     *
     * 系统单号
     */
    @JSONField(name = "trade_no")
    private String tradeNo;
    /**
     * 邮编
     */
    private String zip;
    /**
     * 记录创建时间
     */
    private Date recordCreateTime;
    /**
     * 记录修改时间
     */
    private Date recordUpdateTime;


    public String getOlnOrderStr() {
        return olnOrderStr;
    }

    public void setOlnOrderStr(String olnOrderStr) {
        if (this.olnOrderList != null && this.olnOrderList.size() > 0) {
            this.olnOrderStr = this.olnOrderList.toString();
        }
        //this.olnOrderStr = olnOrderStr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getBuyerMsg() {
        return buyerMsg;
    }

    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(Double discountFee) {
        this.discountFee = discountFee;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public Integer getHasRefund() {
        return hasRefund;
    }

    public void setHasRefund(Integer hasRefund) {
        this.hasRefund = hasRefund;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Boolean getExceptionTrade() {
        return exceptionTrade;
    }

    public void setExceptionTrade(Boolean exceptionTrade) {
        this.exceptionTrade = exceptionTrade;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public Boolean getSmallTrade() {
        return smallTrade;
    }

    public void setSmallTrade(Boolean smallTrade) {
        this.smallTrade = smallTrade;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<String> getOlnOrderList() {
        if (olnOrderList == null && StringUtils.isNotEmpty(olnOrderStr)) {
            String[] result = olnOrderStr.split(",");
            List<String> list = ArrayUtils.converArrayToList(result);
            return list;
        }
        return olnOrderList;
    }

    public void setOlnOrderList(List<String> olnOrderList) {
        this.olnOrderList = olnOrderList;
    }

    public OlnStatus getOlnStatus() {
        return olnStatus;
    }

    public void setOlnStatus(OlnStatus olnStatus) {
        this.olnStatus = olnStatus;
    }

    public List<OpenOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<OpenOrder> orders) {
        this.orders = orders;
    }

    public Double getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(Double paidFee) {
        this.paidFee = paidFee;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPostFee() {
        return postFee;
    }

    public void setPostFee(Double postFee) {
        this.postFee = postFee;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSellerMsg() {
        return sellerMsg;
    }

    public void setSellerMsg(String sellerMsg) {
        this.sellerMsg = sellerMsg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }

    public String getSourcePlatform() {
        return sourcePlatform;
    }

    public void setSourcePlatform(String sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public Double getSumSale() {
        return sumSale;
    }

    public void setSumSale(Double sumSale) {
        this.sumSale = sumSale;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTpTid() {
        return tpTid;
    }

    public void setTpTid(String tpTid) {
        this.tpTid = tpTid;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Date getRecordCreateTime() {
        return recordCreateTime;
    }

    public void setRecordCreateTime(Date recordCreateTime) {
        this.recordCreateTime = recordCreateTime;
    }

    public Date getRecordUpdateTime() {
        return recordUpdateTime;
    }

    public void setRecordUpdateTime(Date recordUpdateTime) {
        this.recordUpdateTime = recordUpdateTime;
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
        OrderStatusResult that = (OrderStatusResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(address, that.address) &&
                Objects.equals(buyer, that.buyer) &&
                Objects.equals(buyerAccount, that.buyerAccount) &&
                Objects.equals(buyerMsg, that.buyerMsg) &&
                Objects.equals(city, that.city) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(discountFee, that.discountFee) &&
                Objects.equals(district, that.district) &&
                Objects.equals(expressCode, that.expressCode) &&
                Objects.equals(hasRefund, that.hasRefund) &&
                Objects.equals(identityNum, that.identityNum) &&
                Objects.equals(invoice, that.invoice) &&
                Objects.equals(exceptionTrade, that.exceptionTrade) &&
                Objects.equals(pay, that.pay) &&
                Objects.equals(smallTrade, that.smallTrade) &&
                Objects.equals(logisticCode, that.logisticCode) &&
                Objects.equals(modifyTime, that.modifyTime) &&
                Objects.equals(olnOrderList, that.olnOrderList) &&
                Objects.equals(olnOrderStr, that.olnOrderStr) &&
                olnStatus == that.olnStatus &&
                Objects.equals(orders, that.orders) &&
                Objects.equals(paidFee, that.paidFee) &&
                Objects.equals(payTime, that.payTime) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(postFee, that.postFee) &&
                Objects.equals(processStatus, that.processStatus) &&
                Objects.equals(province, that.province) &&
                Objects.equals(receiver, that.receiver) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(sellerMsg, that.sellerMsg) &&
                Objects.equals(sendTime, that.sendTime) &&
                Objects.equals(serviceFee, that.serviceFee) &&
                Objects.equals(shopName, that.shopName) &&
                Objects.equals(shopNick, that.shopNick) &&
                Objects.equals(sourcePlatform, that.sourcePlatform) &&
                status == that.status &&
                Objects.equals(storageCode, that.storageCode) &&
                Objects.equals(storageName, that.storageName) &&
                Objects.equals(sumSale, that.sumSale) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(tpTid, that.tpTid) &&
                Objects.equals(tradeNo, that.tradeNo) &&
                Objects.equals(zip, that.zip) &&
                Objects.equals(recordCreateTime, that.recordCreateTime) &&
                Objects.equals(recordUpdateTime, that.recordUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, address, buyer, buyerAccount, buyerMsg, city, createTime, discountFee, district, expressCode, hasRefund, identityNum, invoice, exceptionTrade, pay, smallTrade, logisticCode, modifyTime, olnOrderList, olnOrderStr, olnStatus, orders, paidFee, payTime, phone, postFee, processStatus, province, receiver, remark, sellerMsg, sendTime, serviceFee, shopName, shopNick, sourcePlatform, status, storageCode, storageName, sumSale, tel, tpTid, tradeNo, zip, recordCreateTime, recordUpdateTime);
    }

    @Override
    public String toString() {
        return "OrderStatusResult{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerAccount='" + buyerAccount + '\'' +
                ", buyerMsg='" + buyerMsg + '\'' +
                ", city='" + city + '\'' +
                ", createTime=" + createTime +
                ", discountFee=" + discountFee +
                ", district='" + district + '\'' +
                ", expressCode='" + expressCode + '\'' +
                ", hasRefund=" + hasRefund +
                ", identityNum='" + identityNum + '\'' +
                ", invoice=" + invoice +
                ", exceptionTrade=" + exceptionTrade +
                ", pay=" + pay +
                ", smallTrade=" + smallTrade +
                ", logisticCode='" + logisticCode + '\'' +
                ", modifyTime=" + modifyTime +
                ", olnOrderList=" + olnOrderList +
                ", olnOrderStr='" + olnOrderStr + '\'' +
                ", olnStatus=" + olnStatus +
                ", orders=" + orders +
                ", paidFee=" + paidFee +
                ", payTime=" + payTime +
                ", phone='" + phone + '\'' +
                ", postFee=" + postFee +
                ", processStatus=" + processStatus +
                ", province='" + province + '\'' +
                ", receiver='" + receiver + '\'' +
                ", remark='" + remark + '\'' +
                ", sellerMsg='" + sellerMsg + '\'' +
                ", sendTime=" + sendTime +
                ", serviceFee=" + serviceFee +
                ", shopName='" + shopName + '\'' +
                ", shopNick='" + shopNick + '\'' +
                ", sourcePlatform='" + sourcePlatform + '\'' +
                ", status=" + status +
                ", storageCode='" + storageCode + '\'' +
                ", storageName='" + storageName + '\'' +
                ", sumSale=" + sumSale +
                ", tel='" + tel + '\'' +
                ", tpTid='" + tpTid + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", zip='" + zip + '\'' +
                ", recordCreateTime=" + recordCreateTime +
                ", recordUpdateTime=" + recordUpdateTime +
                '}';
    }
}
