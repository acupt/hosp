package com.acupt.entity.enums;

/**
 * Created by liujie on 2017/9/16.
 */
public enum SicknessTypeEnum {

    HOSPITAL(1, "医院数据"), MANUAL(2, "手工导入"), BAIDU_BAIKE(3, "百度百科");

    private int value;

    private String name;

    SicknessTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static SicknessTypeEnum getByValue(int value) {
        for (SicknessTypeEnum typeEnum : values()) {
            if (typeEnum.getValue() == value) {
                return typeEnum;
            }
        }
        return null;
    }
}
