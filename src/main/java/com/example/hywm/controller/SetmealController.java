package com.example.hywm.controller;

import com.example.hywm.common.Result;
import com.example.hywm.common.WMContonst;
import com.example.hywm.dto.SetmealDto;
import com.example.hywm.service.SetmealService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/12 16:13
 **/
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @PostMapping()
    public Result insertSetmeal(HttpServletRequest request, @RequestBody SetmealDto setmealDto){
        try {
            String id =(String)request.getSession().getAttribute("Employee");
            setmealDto.setCreateUser(id);
            setmealDto.setUpdateUser(id);
            Boolean bool = setmealService.insertSetmeal(setmealDto);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_INSERT.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        }
    }

    @GetMapping("/page")
    public Result querySetmealPage(PageReqVo pageReqVo){
        try {
            PageResult pageResult = setmealService.selectSetmealPage(pageReqVo);
            return Result.success(pageResult);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @GetMapping("/{id}")
    public Result querySetmealById(@PathVariable String id){
        try {
            SetmealDto setmealDto = setmealService.selectSetmealById(id);
            return Result.success(setmealDto);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @PutMapping
    public Result editSetmeal(HttpServletRequest request,@RequestBody SetmealDto setmealDto){
        try {
            String id =(String)request.getSession().getAttribute("Employee");
            setmealDto.setUpdateUser(id);
            Boolean bool = setmealService.editSetmeal(setmealDto);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }

    @DeleteMapping
    public Result deleteSetmel(@RequestParam("ids") String id){
        try {
            Boolean bool = setmealService.deleteSetmeal(id);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_DELETE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        }
    }

    @PostMapping("/status/{status}")
    public Result editSetmealStatus(@PathVariable String status,@RequestParam("ids") String id){
        try {
            Boolean bool = setmealService.editSetmealStatus(status, id);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }
}
