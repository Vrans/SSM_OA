package com.vran.oa.biz.impl;

import com.vran.oa.bean.Department;
import com.vran.oa.biz.DepartmentBiz;
import com.vran.oa.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**部门业务层
 *
 * @ClassName DepartmentBizImpl
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 20:46
 * @Version 1.0
 **/
@Service("departmentBiz")
public class DepartmentBizImpl implements DepartmentBiz {

    @Autowired
    private DepartmentDao departmentDao;
    /**添加部门
     *
     * @methodsName add
     * @date 2019/8/30 20:46
     * @params department
     * @return: void
     */
    @Override
    public void add(Department department) {
        departmentDao.insert(department);
    }

    /**更新部门--根据部门对象
     *
     * @methodsName edit
     * @date 2019/8/30 20:51
     * @params department
     * @return: void
     */
    @Override
    public void edit(Department department) {
        departmentDao.update(department);
    }

    /**根据部门id 删除部门
     *
     * @methodsName remove
     * @date 2019/8/30 20:52
     * @params sn
     * @return: void
     */
    @Override
    public void remove(String sn) {
        departmentDao.delete(sn);
    }

    /**根据部门Id 查找部门
     *
     * @methodsName get
     * @date 2019/8/30 20:52
     * @params sn
     * @return: com.vran.oa.bean.Department
     */
    @Override
    public Department get(String sn) {
        return departmentDao.select(sn);
    }

    /**查找所有部门
     *
     * @methodsName getAll
     * @date 2019/8/30 20:52
     * @params
     * @return: java.util.List<com.vran.oa.bean.Department>
     */
    @Override
    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
