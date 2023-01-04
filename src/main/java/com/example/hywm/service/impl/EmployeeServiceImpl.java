package com.example.hywm.service.impl;

import com.example.hywm.common.PageUtils;
import com.example.hywm.entity.Employee;
import com.example.hywm.mapper.EmployeeMapper;
import com.example.hywm.service.EmployeeService;
import com.example.hywm.vo.EmployeePageReqVo;
import com.example.hywm.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee login(Employee employee) throws Exception{
        Employee em = employeeMapper.selectEmployByName(employee.getUsername());
        return em;
    }
    public void insertEmployee(Employee employee) throws Exception{
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        employee.setId(uuid);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.insertEmploy(employee);
    }
    
    public PageResult selectEmployeePage(EmployeePageReqVo employeePageReqVo) throws Exception{
        List employeeList = new ArrayList<>();
        int page = employeePageReqVo.getPage();
        int pageSize = employeePageReqVo.getPageSize();
        PageHelper.startPage(page,pageSize);
        if(StringUtils.isNotEmpty(employeePageReqVo.getName())){
            String name = "%" + employeePageReqVo.getName() + "%";
            employeeList = employeeMapper.selectEmployeeLikeName(name);
        }else{
            employeeList = employeeMapper.selectAllEmployee();
        }
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        PageResult pageResult = PageUtils.getPageResult(pageInfo);
        return pageResult;
    }

    public Employee selectEmployeeById(String id) throws Exception{
        Employee employee = employeeMapper.selectEmployById(id);
        return employee;
    }

    public void editEmployee(Employee employee) throws Exception{
        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.editEmployeeById(employee);
    }
}
