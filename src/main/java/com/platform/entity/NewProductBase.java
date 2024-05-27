package com.platform.entity;

import java.util.Date;

public class NewProductBase{
	private Integer id;
	private Integer userId; //6
	private String itemNo;
	private String mainCode;
	private String other;
	private String putawayType;
	private String picture;
	private String classify;
	private String color;
	private String shop;
	private String model;
	private Double sellPrice;
	private String size;
	private String barcodeOne;
	private String barcodeTwo;
	private String barcodeThree;
	private String barcodeFour;
	private String barcodeFive;
	private String link;
	private String remark;
	private String sameLink;
	private String creater;
	private String updater;
	private Date updateTime;
	private Date createTime;
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
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getMainCode() {
		return mainCode;
	}
	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}

	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getBarcodeOne() {
		return barcodeOne;
	}
	public void setBarcodeOne(String barcodeOne) {
		this.barcodeOne = barcodeOne;
	}
	public String getBarcodeTwo() {
		return barcodeTwo;
	}
	public void setBarcodeTwo(String barcodeTwo) {
		this.barcodeTwo = barcodeTwo;
	}
	public String getBarcodeThree() {
		return barcodeThree;
	}
	public void setBarcodeThree(String barcodeThree) {
		this.barcodeThree = barcodeThree;
	}
	public String getBarcodeFour() {
		return barcodeFour;
	}
	public void setBarcodeFour(String barcodeFour) {
		this.barcodeFour = barcodeFour;
	}
	public String getBarcodeFive() {
		return barcodeFive;
	}
	public void setBarcodeFive(String barcodeFive) {
		this.barcodeFive = barcodeFive;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSameLink() {
		return sameLink;
	}
	public void setSameLink(String sameLink) {
		this.sameLink = sameLink;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
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

	public String getPutawayType() {
		return putawayType;
	}

	public void setPutawayType(String putawayType) {
		this.putawayType = putawayType;
	}
}
