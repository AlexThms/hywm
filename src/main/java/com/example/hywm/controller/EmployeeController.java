package com.example.hywm.controller;


import com.example.hywm.common.WMContonst;
import com.example.hywm.entity.Employee;
import com.example.hywm.service.EmployeeService;
import com.example.hywm.common.Result;
import com.example.hywm.vo.EmployeePageReqVo;
import com.example.hywm.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/employee")
@RestController
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping( "/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        try {
            String password = employee.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            Employee em = employeeService.login(employee);
            if(em == null){
                return Result.error(WMContonst.ErrorEnum.Error_LOGIN_01.getKey(),WMContonst.ErrorEnum.Error_LOGIN_01.getMsg());
            }
            if(!em.getPassword().equals(password)){
                return Result.error(WMContonst.ErrorEnum.Error_LOGIN_02.getKey(),WMContonst.ErrorEnum.Error_LOGIN_02.getMsg());
            }
            if(em.getStatus().equals("0")){
                return Result.error(WMContonst.ErrorEnum.Error_LOGIN_03.getKey(),WMContonst.ErrorEnum.Error_LOGIN_03.getMsg());
            }else {
                request.getSession().setAttribute("Employee", em.getId());
                return Result.success(em);
            }
        }catch (Exception exception){
            return Result.error("500",exception.getMessage());
        }
    }

    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest req){
        req.getSession().removeAttribute("Employee");
        return Result.success("退出成功");
    }

    @PostMapping
    public Result<String> insertEmployee(HttpServletRequest req, @RequestBody Employee employee){
        try {
            employee.setPassword(DigestUtils.md5DigestAsHex(WMContonst.PASSWORD.getBytes()));
            String id =(String) req.getSession().getAttribute("Employee");
            employee.setCreateUser(id);
            employee.setUpdateUser(id);
            employeeService.insertEmployee(employee);
            return Result.success("添加成功");
        } catch (Exception exception) {
            return Result.error("500","员工已存在，添加失败");
        }
    }

    @GetMapping("/page")
    public Result queryEmployeePage(EmployeePageReqVo employeePageReqVo){
        try {
            log.info("当前页：{}，页大小；{} name：{}", employeePageReqVo.getPage(), employeePageReqVo.getPageSize(), employeePageReqVo.getName());
            PageResult pageResult = employeeService.selectEmployeePage(employeePageReqVo);
            log.info("分页查询返回：{}",pageResult);
            return Result.success(pageResult);
        } catch (Exception exception) {
            return Result.error("500","分页查询失败");
        }
    }

    @GetMapping("/{id}")
    public Result queryEmployeeById(@PathVariable String id){
        try {
            Employee employee = employeeService.selectEmployeeById(id);
            return Result.success(employee);
        } catch (Exception exception) {
            return Result.error("500","查询失败");
        }
    }

    @PutMapping
    public Result editEmployee(HttpServletRequest req, @RequestBody Employee employee){
        try{
            String id =(String) req.getSession().getAttribute("Employee");
            employee.setUpdateUser(id);
            employeeService.editEmployee(employee);
            return Result.success("修改成功");
        }catch (Exception exception) {
            return Result.error("500","修改信息失败");
        }
    }

}
