package com.platform.entity;

import java.util.Objects;

/**
* @Author:zhuhaojie
* @Date:10:28 2018/12/12
 * 批次信息
*/
public class Batchs {


    /**
     * 批次号
     */
    private String batchCode;
    /**
     * 批次库存
     */
    private Double quantity;

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Batchs batchs = (Batchs) o;
        return Objects.equals(batchCode, batchs.batchCode) &&
                Objects.equals(quantity, batchs.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchCode, quantity);
    }

    @Override
    public String toString() {
        return "Batchs{" +
                "batchCode='" + batchCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
