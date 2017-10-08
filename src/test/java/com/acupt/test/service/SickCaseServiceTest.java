package com.acupt.test.service;

import com.acupt.entity.enums.SickCaseTypeEnum;
import com.acupt.entity.es.SickCase;
import com.acupt.service.SickCaseService;
import com.acupt.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by liujie on 2017/9/30.
 */
public class SickCaseServiceTest extends BaseTest {

    @Resource
    private SickCaseService sickCaseService;

    @Test
    public void testInsert() {
        SickCase sickCase = new SickCase();
        sickCase.setTitle("测试病例1");
        sickCase.setContent("测试一下SDASDAS");
        sickCase.setCt(System.currentTimeMillis());
        sickCase.setType(SickCaseTypeEnum.USER_SUBMIT.getValue());
        sickCase.setUid(1L);
        print(sickCaseService.insert(sickCase));
    }

    @Test
    public void testGetList() {
//        print(sickCaseService.);
//        print(sickCaseService.page(1L, 1, 10));
    }
}
