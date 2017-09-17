package com.acupt.hosp.web;

import com.acupt.entity.User;
import com.acupt.hosp.domain.enums.NavEnum;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujie on 2017/9/15.
 */
public class LoginContext implements Serializable {

    private static final long serialVersionUID = -1857541855641247126L;

    private User user;

    private long loginTime;

    private List<NavEnum> nav;

    private LoginContext(User user) {
        this.user = user;
        this.loginTime = System.currentTimeMillis();
        nav = new ArrayList<>();
        for (NavEnum navEnum : NavEnum.values()) {
            if (navEnum.getRole() == user.getRole()) {
                nav.add(navEnum);
            }
        }
    }

    public static LoginContext get(HttpServletRequest request) {
        return (LoginContext) request.getSession().getAttribute(SessionKey.LOGIN_CONTEXT);
    }

    public static void login(HttpServletRequest request, User user) {
        request.getSession().setAttribute(SessionKey.LOGIN_CONTEXT, new LoginContext(user));
    }

    public static void logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionKey.LOGIN_CONTEXT);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public List<NavEnum> getNav() {
        return nav;
    }

    public void setNav(List<NavEnum> nav) {
        this.nav = nav;
    }
}
