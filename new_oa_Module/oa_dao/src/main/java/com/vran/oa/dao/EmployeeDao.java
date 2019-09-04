package com.vran.oa.dao;

import com.vran.oa.bean.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {

    /*增加用户*/
    void insert(Employee employee);

    /*删除用户*/
    void delete(String sn);

    /*修改用户*/
    void update(Employee employee);

    /*根据id查询用户*/
    Employee select(String sn);

    /*查询全部用户*/
    List<Employee> selectAll();

    /*查询用户的部门经理*/
    List<Employee> selectDepartmentAndPost(@Param("dsn") String dsn,@Param("post") String post);

}
