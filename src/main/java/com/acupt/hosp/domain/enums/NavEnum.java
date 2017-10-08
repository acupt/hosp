package com.acupt.hosp.domain.enums;

import com.acupt.entity.enums.UserRoleEnum;

/**
 * Created by liujie on 2017/9/17.
 */
public enum NavEnum {

    NEW("NEW", "/sick/new", UserRoleEnum.NORMAL.getValue()),
    DIAGNOSE("DIAGNOSE", "/sick/diagnose", UserRoleEnum.DOCTOR.getValue()),
    HISTORY("HISTORY", "/sick/history", UserRoleEnum.merge(UserRoleEnum.NORMAL, UserRoleEnum.DOCTOR)),
    IMPORT("IMPORT", "/sick/import", UserRoleEnum.ADMIN.getValue()),
    LOG("LOG", "/log", UserRoleEnum.ADMIN.getValue()),;

    private String name;

    private String uri;

    private int role;

    NavEnum(String name, String uri, int role) {
        this.name = name;
        this.uri = uri;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public int getRole() {
        return role;
    }
}
