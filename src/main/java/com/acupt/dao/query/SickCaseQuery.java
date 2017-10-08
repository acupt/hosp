package com.acupt.dao.query;

/**
 * @see com.acupt.entity.es.SickCase
 * Created by liujie on 2017/10/7.
 */
public class SickCaseQuery extends PageableQuery {

    private Long uid;

    private Long drUid;

    private Boolean processed;

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

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }
}
