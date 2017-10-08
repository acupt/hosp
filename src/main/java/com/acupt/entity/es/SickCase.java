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
    private long uid;

    /**
     * 确诊此病例的医生ID
     *
     * @see com.acupt.entity.User#id
     */
    private long drUid;

    /**
     * @see com.acupt.entity.Sickness#id
     */
    private long sid;

    /**
     * 医生处理结果
     */
    private String drRemarks;

    /**
     * @see com.acupt.entity.enums.SickCaseTypeEnum
     */
    private int type;

    /**
     * 创建时间
     */
    private long ct;

    /**
     * 确诊时间
     */
    private long et;

    /**
     * 已处理
     */
    private boolean processed;

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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getDrUid() {
        return drUid;
    }

    public void setDrUid(long drUid) {
        this.drUid = drUid;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getDrRemarks() {
        return drRemarks;
    }

    public void setDrRemarks(String drRemarks) {
        this.drRemarks = drRemarks;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCt() {
        return ct;
    }

    public void setCt(long ct) {
        this.ct = ct;
    }

    public long getEt() {
        return et;
    }

    public void setEt(long et) {
        this.et = et;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
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
                ", processed=" + processed +
                '}';
    }
}
