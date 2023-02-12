package com.example.hywm.service.impl;

import com.example.hywm.common.PageUtils;
import com.example.hywm.entity.Employee;
import com.example.hywm.mapper.EmployeeMapper;
import com.example.hywm.service.EmployeeService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee login(Employee employee) throws Exception{
        Employee em = employeeMapper.selectEmployByName(employee.getUsername());
        return em;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertEmployee(Employee employee) throws Exception{
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        employee.setId(uuid);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Integer integer = employeeMapper.insertEmploy(employee);
        if (integer != 1){
            return false;
        }
        return true;
    }

    @Override
    public PageResult selectEmployeePage(PageReqVo pageReqVo) throws Exception{
        List employeeList = new ArrayList<>();
        int page = pageReqVo.getPage();
        int pageSize = pageReqVo.getPageSize();
        Page<Object> pageHelp = PageHelper.startPage(page, pageSize);
        if(StringUtils.isNotEmpty(pageReqVo.getName())){
            String name = "%" + pageReqVo.getName() + "%";
            employeeList = employeeMapper.selectEmployeeLikeName(name);
        }else{
            employeeList = employeeMapper.selectAllEmployee();
        }
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        PageResult pageResult = PageUtils.getPageResult(pageInfo,pageHelp);
        return pageResult;
    }

    @Override
    public Employee selectEmployeeById(String id) throws Exception{
        Employee employee = employeeMapper.selectEmployById(id);
        return employee;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editEmployee(Employee employee) throws Exception{
        employee.setUpdateTime(LocalDateTime.now());
        Integer integer = employeeMapper.editEmployeeById(employee);
        if (integer != 1){
            return false;
        }
        return true;
    }
}
