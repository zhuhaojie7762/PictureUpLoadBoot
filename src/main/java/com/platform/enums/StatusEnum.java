package com.platform.enums;

/**
 * 系统状态枚举
 *
 * @Author:zhuhaojie
 * @Date:15:56 2018/12/14
 */
public enum StatusEnum {
    /**
     * 系统状态1：处理中 2：发货 3：完成 4: 关闭 5:其他
     */
    PROCESS("处理中", 1),
    SEND("发货", 2),
    FINISH("完成", 3),
    CLOSED("关闭", 4),
    OTHER("其他", 5);

    private String name;
    private Integer code;

    StatusEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }


    public Integer getCode(String name) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getName().equals(name)) {
                return statusEnum.getCode();
            }
        }
        return null;
    }

    public String getName(Integer code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum.getName();
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
