package com.platform.enums;

import java.util.Objects;

/**
 * 发票类型枚举
 *
 * @Author:zhuhaojie
 * @Date:16:39 2018/12/14
 */
public enum InvoiceType {
    PLAIN_INVOICE("普通发票", 1),
    ADD_VALUE_TAX_INVOICE("增值税普通发票", 2),
    ELECT_INVOICE("电子增票", 3);
    /**
     * type	Int	发票类型,  1=普通发票，2=增值税普通发票, 3=电子增票
     */

    private String name;
    private Integer code;

    InvoiceType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }


    public Integer getCode(String name) {
        for (InvoiceType invoiceType : InvoiceType.values()) {
            if (invoiceType.getName().equals(name)) {
                return invoiceType.getCode();
            }
        }
        return null;
    }

    public String getName(Integer code) {
        for (InvoiceType invoiceType : InvoiceType.values()) {
            if (Objects.equals(invoiceType.getCode(), code)) {
                return invoiceType.getName();
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
