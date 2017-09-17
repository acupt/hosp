package com.acupt.entity.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * Created by liujie on 2017/9/4.
 */
@Document(indexName = "hosp", type = "sickcase")
public class SickCase implements Serializable {

    @Id
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 病情描述or用户的描述
     */
    private String content;

    /**
     * 在网站看病的用户ID
     *
     * @see com.acupt.entity.User#id
     */
    private Long uid;

    /**
     * 确诊此病例的医生ID
     *
     * @see com.acupt.entity.User#id
     */
    private Long drUid;

    /**
     * @see com.acupt.entity.Sickness#id
     */
    private Long sid;

    /**
     * 医生处理结果
     */
    private String drRemarks;

    /**
     * @see com.acupt.entity.enums.SickCaseTypeEnum
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Long ct;

    /**
     * 确诊时间
     */
    private Long et;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getDrUid() {
        return drUid;
    }

    public void setDrUid(Long drUid) {
        this.drUid = drUid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getDrRemarks() {
        return drRemarks;
    }

    public void setDrRemarks(String drRemarks) {
        this.drRemarks = drRemarks;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
    }

    public Long getEt() {
        return et;
    }

    public void setEt(Long et) {
        this.et = et;
    }

    @Override
    public String toString() {
        return "SickCase{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", drUid=" + drUid +
                ", sid=" + sid +
                ", drRemarks='" + drRemarks + '\'' +
                ", type=" + type +
                ", ct=" + ct +
                ", et=" + et +
                '}';
    }
}
