package com.vran.oa.controller;

import com.vran.oa.bean.Employee;
import com.vran.oa.biz.DepartmentBiz;
import com.vran.oa.biz.EmployeeBiz;
import com.vran.oa.util.Contant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @ClassName EmployeeController
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 16:51
 * @Version 1.0
 **/
@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeBiz employeeBiz;
    @Autowired
    private DepartmentBiz departmentBiz;

    /*去添加*/
    @RequestMapping("to_add")
    public String toAdd(Map<String,Object>map) {
        map.put("employee", new Employee());
        map.put("dlist", departmentBiz.getAll());
        map.put("plist", Contant.getPosts());
        return "employee_add";
    }

    /*添加*/
    @RequestMapping("/add")
    public String add(Employee employee) {
        employeeBiz.add(employee);
        return "redirect:list";
    }

    /*删除*/
    @RequestMapping("/remove")
    public String delete(@Param("sn") String sn) {
        employeeBiz.remove(sn);
        return "redirect:list";
    }

    /*去修改*/
    @RequestMapping("/to_update")
    public String toUpdate(@Param("sn") String sn, Map<String, Object> map) {
        map.put("employee", employeeBiz.get(sn));
        map.put("dlist", departmentBiz.getAll());
        map.put("plist", Contant.getPosts());
        return "employee_update";
    }

    /*修改*/
    @RequestMapping("/update")
    public String update(Employee employee) {
        employeeBiz.add(employee);
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String employee_list(Map<String, Object>map) {
        List<Employee> list = employeeBiz.getAll();
        map.put("list", list);
        return "employee_list";
    }


}
