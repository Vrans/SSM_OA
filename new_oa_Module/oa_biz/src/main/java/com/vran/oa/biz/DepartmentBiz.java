package com.vran.oa.biz;

import com.vran.oa.bean.Department;

import java.util.List;

/**
 * @ClassName DepartmentBiz
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 20:44
 * @Version 1.0
 **/
public interface DepartmentBiz {
    void add(Department department);

    void edit(Department department);

    void remove(String sn);

    Department get(String sn);

    List<Department> getAll();



}
