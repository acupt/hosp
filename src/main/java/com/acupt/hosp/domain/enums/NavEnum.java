package com.acupt.hosp.domain.enums;

import com.acupt.entity.enums.UserRoleEnum;

/**
 * Created by liujie on 2017/9/17.
 */
public enum NavEnum {

    NEW("求医", "/sick/new", UserRoleEnum.NORMAL.getValue()),
    HISTORY("求医记录", "/sick/history", UserRoleEnum.NORMAL.getValue()),
    DIAGNOSE("诊断", "/sick/diagnose", UserRoleEnum.DOCTOR.getValue()),
    DIAGNOSE_HISTORY("诊断记录", "/sick/new", UserRoleEnum.DOCTOR.getValue()),
    IMPORT("导入数据", "/sick/import", UserRoleEnum.ADMIN.getValue()),
    LOG("操作记录", "/log/new", UserRoleEnum.ADMIN.getValue()),;

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
