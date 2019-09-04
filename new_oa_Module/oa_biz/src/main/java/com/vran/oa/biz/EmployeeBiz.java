package com.vran.oa.biz;

import com.vran.oa.bean.Employee;

import java.util.List;

/**
 * @ClassName EmployeeBiz
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 16:41
 * @Version 1.0
 **/

public interface EmployeeBiz {
    void add(Employee employee);

    void remove(String sn);

    void edit(Employee employee);

    Employee get(String sn);

    List<Employee> getAll();
}
