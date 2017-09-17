package com.acupt.util;

import org.springframework.util.DigestUtils;

/**
 * Created by liujie on 2017/9/14.
 */
public class PasswordUtil {

    public static String encrypt(String password) {
        password = "gg" + password;
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public static String checkPasswordFormat(String pwd) {
        if (StringUtil.isBlank(pwd)) {
            return "密码不能为空";
        }
        if (pwd.length() < 4) {
            return "密码长度不能少于4个字符";
        }
        if (pwd.length() > 20) {
            return "密码长度不能超过20个字符";
        }
        if (pwd.contains(" ")) {
            return "密码不能包含空格";
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("123qwe"));
        System.out.println(encrypt(""));
        System.out.println(encrypt(null));
    }
}
