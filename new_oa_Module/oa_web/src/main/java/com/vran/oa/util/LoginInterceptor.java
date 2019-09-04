package com.vran.oa.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/15 19:19
 * @Version 1.0
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        /*toLowerCase将转为小写----indexOf是查找有没有login这个字符*/
        if (url.toLowerCase().indexOf("login") >= 0) {
            return true;
        }

        /*判断是否是登陆状态下session*/
        HttpSession session = request.getSession();
        if (session.getAttribute("employee") != null) {
            return true;
        }
        /*如果没登陆直接返回登陆界面*/
        response.sendRedirect("/to_login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
