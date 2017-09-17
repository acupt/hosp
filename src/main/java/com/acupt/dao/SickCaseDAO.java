package com.acupt.dao;

import com.acupt.entity.es.SickCase;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * Created by liujie on 2017/9/4.
 */
public interface SickCaseDAO extends ElasticsearchCrudRepository<SickCase, String> {

    List<SickCase> findByContent(String content);
}
