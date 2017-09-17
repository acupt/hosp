package com.acupt.hosp.filter;

import com.acupt.domain.Result;
import com.acupt.domain.enums.CodeEnum;
import com.acupt.hosp.util.ContextUtil;
import com.acupt.hosp.web.LoginContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liujie on 2017/8/15.
 */
@WebFilter(filterName = "requestFilter", urlPatterns = "/*")
public class RequestFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long t0 = System.currentTimeMillis();
        String uri = request.getRequestURI();
        boolean isStatic = isStatic(uri);
        if (!isStatic && !loginFilter(request)) {
            response.setContentType("text/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Result(CodeEnum.NO_LOGIN).toString());
            return;
        }
        filterChain.doFilter(request, response);
        if (!isStatic) {
            logger.info("{}:{},ip:{},cost:{}ms,agent:{}", request.getMethod(), uri, ContextUtil.getRemoteIp(request),
                    System.currentTimeMillis() - t0, ContextUtil.getAgent(request));
        }
    }

    private boolean isStatic(String uri) {
        if (uri.startsWith("/css/")) {
            return true;
        }
        if (uri.startsWith("/fonts/")) {
            return true;
        }
        if (uri.startsWith("/img/")) {
            return true;
        }
        if (uri.startsWith("/js/")) {
            return true;
        }
        if (uri.equals("/favicon.ico")) {
            return true;
        }
        return false;
    }

    private boolean loginFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri.indexOf('/', 1) < 0) {
            return true;
        }
        LoginContext loginContext = LoginContext.get(request);
        return loginContext != null;
    }
}
