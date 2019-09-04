package com.vran.oa.biz.impl;

import com.vran.oa.bean.Employee;
import com.vran.oa.biz.EmployeeBiz;
import com.vran.oa.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeBizImpl
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 16:42
 * @Version 1.0
 **/

@Service("employeeBiz")
public class EmployeeBizImpl implements EmployeeBiz {
    @Autowired
    private EmployeeDao employeeDao;

    /*添加用户的时候设置默认密码*/
    @Override
    public void add(Employee employee) {
        employee.setPassword("000000");
        employeeDao.insert(employee);
    }

    @Override
    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    @Override
    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
