package com.acupt.entity;


import com.acupt.entity.enums.UserRoleEnum;
import com.acupt.util.TagUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * Created by liujie on 2017/8/16.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_name", columnNames = {"name"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String password;

    private String nick;

    private String remarks;

    /**
     * @see com.acupt.entity.enums.UserRoleEnum
     */
    private int role;

    private Date gmtCreated;

    private Date gmtModified;

    public boolean isNormal() {
        return TagUtil.hasTag(role, UserRoleEnum.NORMAL.getValue());
    }

    public boolean isDoctor() {
        return TagUtil.hasTag(role, UserRoleEnum.DOCTOR.getValue());
    }

    public boolean isAdmin() {
        return TagUtil.hasTag(role, UserRoleEnum.ADMIN.getValue());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
