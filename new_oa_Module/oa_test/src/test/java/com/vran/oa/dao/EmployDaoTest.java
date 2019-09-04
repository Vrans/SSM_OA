package com.vran.oa.dao;

import com.vran.oa.BastTest;
import com.vran.oa.bean.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName EmployDaoTest
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 16:19
 * @Version 1.0
 **/
public class EmployDaoTest extends BastTest {
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void test01() {
        List<Employee> list = employeeDao.selectAll();
        Assert.assertEquals(5,list.size());
    }

}
