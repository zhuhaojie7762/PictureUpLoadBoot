package com.platform.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class NewProductBase implements Serializable {
    private Integer id;
    private Integer userId;
    private String itemNo;
    private String mainCode;
    private String other;
    private String putawayType;
    private String picture;
    private String classify;
    private String color;
    private String shop;
    private String model;
    private BigDecimal sellPrice;
    private String productSize;
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
}
