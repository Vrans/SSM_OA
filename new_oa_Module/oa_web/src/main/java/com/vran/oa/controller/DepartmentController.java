package com.vran.oa.controller;

import com.vran.oa.bean.Department;
import com.vran.oa.biz.DepartmentBiz;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @ClassName DepartmentController
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 20:54
 * @Version 1.0
 **/
@Controller("departmentController")
/*统一地址标识符*/
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentBiz departmentBiz;

    /*查看全部部门*/
    @RequestMapping("/list")
    public String list(Map<String, Object> map) {
        map.put("list", departmentBiz.getAll());
        return "department_list";
    }

    /*去_添加部门*/
    @RequestMapping("to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("department", new Department());
        return "department_add";
    }

    /*添加部门*/
    @RequestMapping("add")
    public String add(Department department) {
        departmentBiz.add(department);
        return "redirect:list";
    }

    /*根据Id删除部门*/
    @RequestMapping("/remove")
    public String remove(@Param("sn") String sn) {
        departmentBiz.remove(sn);
        return "redirect:list";
    }

    /*去更新部门----先查找到这个部门*/
    /*将department对象放入到map中，页面将从map集合里获取出department*/
    @RequestMapping("/to_update")
    public String toUpdate(@Param("sn") String sn,Map<String,Object>map) {
        Department department = departmentBiz.get(sn);
        map.put("department", department);
        return "department_update";
    }

    /*更新部门 ----更新department对象*/
    @RequestMapping("/update")
    public String update(Department department) {
        departmentBiz.edit(department);
        return "redirect:list";
    }

}
