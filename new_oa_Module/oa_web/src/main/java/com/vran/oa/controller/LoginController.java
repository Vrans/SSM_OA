package com.vran.oa.controller;

import com.vran.oa.bean.Employee;
import com.vran.oa.biz.LoginBiz;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/31 0:47
 * @Version 1.0
 **/

@Controller("loginController")
public class LoginController {
    @Autowired
    private LoginBiz loginBiz;

    /*跳转登陆界面*/
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    /*登陆*/
    @RequestMapping("/login")
    public String login(@Param("sn") String sn, @Param("password") String password, HttpSession session) {
        Employee employee = loginBiz.login(sn, password);
        /*判断是账号密码是否为空*/
        if (employee == null) {
            return "redirect:to_login";
        }
        /*将当前登录账号放到 session里面，用来获取用户的数据*/
        session.setAttribute("employee", employee);
        return "redirect:self";
    }

    /*跳转个人信息*/
    @RequestMapping("/self")
    public String self() {
        return "self";
    }

    /*退出*/
    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.setAttribute("employee", null);
        return "redirect:to_login";
    }

    /*去更改*/
    @RequestMapping("to_change_password")
    public String toChange() {
        return "change_password";
    }

    /*更改密码*/
    @RequestMapping("/change_password")
    public String changePassword(HttpSession session, @RequestParam String oldPwd,@RequestParam String newPwd1 ,@RequestParam String newPwd2) {
        /*获取当前用户*/
        Employee employee= (Employee) session.getAttribute("employee");
        if (employee.getPassword().equals(oldPwd)) {
            employee.setPassword(newPwd1);
            /*更新用户密码*/
            loginBiz.changePassword(employee);
            return "redirect:self";
        }
        return "redirect:to_change_password";



    }

}
