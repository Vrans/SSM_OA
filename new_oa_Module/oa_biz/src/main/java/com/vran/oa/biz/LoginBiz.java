package com.vran.oa.biz;

import com.vran.oa.bean.Employee;

public interface LoginBiz {
    /*登陆*/
    Employee login(String sn, String password);

    /*修改个人信息*/
    void changePassword(Employee employee);
}
