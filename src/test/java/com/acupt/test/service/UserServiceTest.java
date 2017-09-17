package com.acupt.test.service;

import com.acupt.entity.User;
import com.acupt.entity.enums.UserRoleEnum;
import com.acupt.service.UserService;
import com.acupt.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by liujie on 2017/9/4.
 */
public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("zcy123");
        user.setPassword("123123");
        user.setNick("周胖胖");
        user.setRole(UserRoleEnum.NORMAL.getValue());
        user.setRemarks("谁来救救我");
        print(userService.register(user));
    }

}
