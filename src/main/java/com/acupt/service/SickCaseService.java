package com.acupt.service;

import com.acupt.dao.SickCaseDAO;
import com.acupt.dao.query.SickCaseQuery;
import com.acupt.domain.Result;
import com.acupt.domain.enums.CodeEnum;
import com.acupt.entity.es.SickCase;
import com.acupt.util.StringUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liujie on 2017/9/26.
 */
@Service
public class SickCaseService {

    @Resource
    private SickCaseDAO sickCaseDAO;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    public Result<SickCase> get(String id) {
        return new Result<SickCase>().setData(sickCaseDAO.findOne(id));
    }

    public Result<SickCase> insert(SickCase sickCase) {
        if (StringUtil.isBlank(sickCase.getTitle())) {
            return new Result<>(CodeEnum.PARAM_MISS, "标题不可少");
        }
        if (StringUtil.isBlank(sickCase.getContent())) {
            return new Result<>(CodeEnum.PARAM_MISS, "病情描述不可少");
        }
        sickCase = sickCaseDAO.save(sickCase);
        return new Result<SickCase>().setData(sickCase);
    }

    public Result<List<SickCase>> query(SickCaseQuery query) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if (query.getUid() != null) {
            builder.withFilter(QueryBuilders.matchQuery("uid", query.getUid()));
        }
        if (query.getDrUid() != null) {
            builder.withFilter(QueryBuilders.matchQuery("drUid", query.getDrUid()));
        }
        if (query.getProcessed() != null) {
            builder.withFilter(QueryBuilders.matchQuery("processed", query.getProcessed()));
        }
        builder.withPageable(new PageRequest(query.get_page() - 1, query.get_size()));
        List<SickCase> list = elasticsearchTemplate.queryForList(builder.build(), SickCase.class);
        return new Result<List<SickCase>>().setData(list);
    }

    public Result<SickCase> update(SickCase sickCase) {
        return new Result<SickCase>().setData(sickCaseDAO.save(sickCase));
    }

}
