package com.example.hywm.service;


import com.example.hywm.entity.Employee;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;


public interface EmployeeService {
    public Employee login(Employee employee) throws Exception;

    public Boolean insertEmployee(Employee employee) throws Exception;

    public PageResult selectEmployeePage(PageReqVo pageReqVo) throws Exception;

    public Employee selectEmployeeById(String id) throws Exception;

    public Boolean editEmployee(Employee employee) throws Exception;
}
