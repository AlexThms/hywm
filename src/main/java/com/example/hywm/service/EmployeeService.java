package com.example.hywm.service;


import com.example.hywm.entity.Employee;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;


public interface EmployeeService {
    Employee login(Employee employee) throws Exception;

    Boolean insertEmployee(Employee employee) throws Exception;

    PageResult selectEmployeePage(PageReqVo pageReqVo) throws Exception;

    Employee selectEmployeeById(String id) throws Exception;

    Boolean editEmployee(Employee employee) throws Exception;
}
