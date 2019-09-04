package com.vran.oa.controller;

import com.vran.oa.BastTest;
import com.vran.oa.bean.Employee;
import com.vran.oa.biz.EmployeeBiz;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName EmployeeBizTest
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 16:44
 * @Version 1.0
 **/

public class EmployeeBizTest extends BastTest {
    @Autowired
    private EmployeeBiz employeeBiz;

    @Test
    public void test01() {
        List<Employee> list = employeeBiz.getAll();
        Assert.assertEquals("关羽",list.get(2).getName());
    }

}
