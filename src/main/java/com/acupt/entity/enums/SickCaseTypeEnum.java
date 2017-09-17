package com.acupt.entity.enums;

/**
 * Created by liujie on 2017/9/16.
 */
public enum SickCaseTypeEnum {

    DB_IMPORT(1, "历史病例（医院数据导入）"), WEB_CONFIRMED(2, "确诊病例（网站医生确诊）");

    private int value;

    private String name;

    SickCaseTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static SickCaseTypeEnum getByValue(int value) {
        for (SickCaseTypeEnum typeEnum : values()) {
            if (typeEnum.getValue() == value) {
                return typeEnum;
            }
        }
        return null;
    }
}
