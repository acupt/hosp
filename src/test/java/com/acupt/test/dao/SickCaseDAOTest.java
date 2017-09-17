package com.acupt.test.dao;

import com.acupt.dao.SickCaseDAO;
import com.acupt.entity.enums.SickCaseTypeEnum;
import com.acupt.entity.es.SickCase;
import com.acupt.test.BaseTest;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import javax.annotation.Resource;

/**
 * Created by liujie on 2017/9/4.
 */
public class SickCaseDAOTest extends BaseTest {

    @Resource
    private SickCaseDAO sickCaseDAO;

    @Resource
    private ElasticsearchTemplate esTemplate;

//    @Before
//    public void before() {
//        esTemplate.deleteIndex(Sickness.class);
//        esTemplate.createIndex(Sickness.class);
//        esTemplate.putMapping(Sickness.class);
//        esTemplate.refresh(Sickness.class);
//    }

    @Test
    public void testInsert() {
        for (int i = 1; i <= 5; i++) {
            SickCase sickCase = new SickCase();
            sickCase.setTitle("感冒地" + i + "次发作");
            sickCase.setContent("又感冒了，第" + i + "次");
            sickCase.setType(SickCaseTypeEnum.DB_IMPORT.getValue());
            print(sickCaseDAO.save(sickCase));
        }
    }

    @Test
    public void testGet() {
        print(sickCaseDAO.findByContent("编译"));
    }
}
