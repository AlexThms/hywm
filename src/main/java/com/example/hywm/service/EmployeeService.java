package com.example.hywm.service;


import com.example.hywm.entity.Employee;
import com.example.hywm.vo.EmployeePageReqVo;
import com.example.hywm.vo.PageResult;


public interface EmployeeService {
    public Employee login(Employee employee) throws Exception;

    public void insertEmployee(Employee employee) throws Exception;

    public PageResult selectEmployeePage(EmployeePageReqVo employeePageReqVo) throws Exception;

    public Employee selectEmployeeById(String id) throws Exception;

    public void editEmployee(Employee employee) throws Exception;
}
