package com.platform.entity;



import com.platform.enums.InvoiceType;

import java.util.Objects;

/**
 * 发票实体
* @Author:zhuhaojie
* @Date:16:33 2018/12/14
*/
public class Invoice {
    /**
     * 	发票类型,  1=普通发票，2=增值税普通发票, 3=电子增票
     */
    private InvoiceType type;
    /**
     * 发票抬头
     */
    private String header;
    /**
     * 发票总金额
     */
    private Double amount;

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return type == invoice.type &&
                Objects.equals(header, invoice.header) &&
                Objects.equals(amount, invoice.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, header, amount);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "type=" + type +
                ", header='" + header + '\'' +
                ", amount=" + amount +
                '}';
    }
}
