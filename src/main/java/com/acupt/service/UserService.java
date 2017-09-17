package com.acupt.service;

import com.acupt.dao.UserDAO;
import com.acupt.domain.Result;
import com.acupt.domain.enums.CodeEnum;
import com.acupt.entity.User;
import com.acupt.entity.enums.UserRoleEnum;
import com.acupt.util.PasswordUtil;
import com.acupt.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liujie on 2017/9/14.
 */
@Service
public class UserService {

    @Resource
    private UserDAO userDAO;

    public Result<User> register(User user) {
        String err = checkUser(user);
        if (err != null) {
            return new Result<>(CodeEnum.USER_INFO_ILLEGAL, err);
        }
        if (user.getRole() == UserRoleEnum.ADMIN.getValue()) {
            return new Result<>(CodeEnum.PARAM_ERR, "用户类型错误");
        }
        User exist = userDAO.getByName(user.getName());
        if (exist != null) {
            return new Result<>(CodeEnum.RECORD_DUPLICATE, "用户名已存在");
        }
        user.setGmtCreated(new Date());
        user.setGmtModified(new Date());
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        user = userDAO.save(user);
        return new Result<User>().setData(user);
    }

    public Result<User> login(String name, String password) {
        if (StringUtil.isBlank(name)) {
            return new Result(CodeEnum.PARAM_MISS, "请输入用户名");
        }
        if (StringUtil.isBlank(password)) {
            return new Result(CodeEnum.PARAM_MISS, "请输入密码");
        }
        User user = userDAO.getByName(name);
        if (user == null) {
            return new Result(CodeEnum.USER_NOT_EXIST);
        }
        if (!PasswordUtil.encrypt(password).equals(user.getPassword())) {
            return new Result(CodeEnum.PWD_ERR);
        }
        return new Result<User>().setData(user);
    }


    private String checkUser(User user) {
        if (user == null) {
            return "空表单";
        }
        String err = PasswordUtil.checkPasswordFormat(user.getPassword());
        if (err != null) {
            return err;
        }
        if (user.getName() == null || user.getName().length() < 4) {
            return "用户名长度至少4个字符";
        }
        if (user.getName().length() > 20) {
            return "用户名长度最多20个字符";
        }
        UserRoleEnum roleEnum = UserRoleEnum.getByValue(user.getRole());
        if (roleEnum == null) {
            return "wtf role?";
        }
        return null;
    }
}
