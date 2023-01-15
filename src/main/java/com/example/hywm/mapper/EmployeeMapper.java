package com.example.hywm.mapper;

import com.example.hywm.entity.Employee;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Employee selectEmployByName(String username);

    /**
     * 插入
     * @param employee
     * @return
     */
    Integer insertEmploy(Employee employee);

    /**
     * 查询全部
     * @return
     */
    List<Employee> selectAllEmployee();

    /**
     * 模糊查询
     * @param name
     * @return
     */
    List<Employee> selectEmployeeLikeName(String name);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Employee selectEmployById(String id);

    /**
     * 修改
     * @param employee
     * @return
     */
    Integer editEmployeeById(Employee employee);
}
