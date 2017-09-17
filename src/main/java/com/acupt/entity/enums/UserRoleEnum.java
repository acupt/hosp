package com.acupt.entity.enums;

import com.acupt.util.TagUtil;

/**
 * Created by liujie on 2017/9/15.
 */
public enum UserRoleEnum {

    NORMAL(1 << 0, "普通"), DOCTOR(1 << 1, "医生"), ADMIN(1 << 2, "管理员");

    private int value;

    private String name;

    UserRoleEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static UserRoleEnum getByValue(int value) {
        for (UserRoleEnum roleEnum : values()) {
            if (roleEnum.getValue() == value) {
                return roleEnum;
            }
        }
        return null;
    }

    public static int merge(UserRoleEnum... roles) {
        long value = 0;
        if (roles != null) {
            for (UserRoleEnum roleEnum : roles) {
                value = TagUtil.addTag(value, roleEnum.getValue());
            }
        }
        return (int) value;
    }

    public static int mergeAll() {
        long value = 0;
        for (UserRoleEnum roleEnum : values()) {
            value = TagUtil.addTag(value, roleEnum.getValue());
        }
        return (int) value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
