package com.vran.oa.biz.impl;

import com.vran.oa.bean.Employee;
import com.vran.oa.biz.EmployeeBiz;
import com.vran.oa.biz.LoginBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginBizImpl
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/31 0:43
 * @Version 1.0
 **/
@Service("loginBiz")
public class LoginBizImpl implements LoginBiz {
    /*获取用户的对象*/
    @Autowired
    private EmployeeBiz employeeBiz;

    @Override
    public Employee login(String sn, String password) {
        Employee employee = employeeBiz.get(sn);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }

    @Override
    public void changePassword(Employee employee) {
        employeeBiz.edit(employee);
    }
}
