package com.example.hywm.controller;

import com.example.hywm.common.Result;
import com.example.hywm.common.WMContonst;
import com.example.hywm.entity.Category;
import com.example.hywm.service.CategoryService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
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
 * Description 分类管理
 * Author lihao
 *
 * @Date 2023/1/15 15:35
 **/
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/page")
    public Result queryCategoryPage(PageReqVo pageReqVo){
        try {
            log.info("当前页：{}，页大小；{} name：{}", pageReqVo.getPage(), pageReqVo.getPageSize(), pageReqVo.getName());
            PageResult pageResult = categoryService.selectCategoryPage(pageReqVo);
            log.info("分页查询返回：{}",pageResult);
            return Result.success(pageResult);
        } catch (Exception exception) {
          return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @PostMapping
    public Result insertCategory(HttpServletRequest req ,@RequestBody Category category){
        try {
            String id =(String) req.getSession().getAttribute("Employee");
            category.setCreateUser(id);
            category.setUpdateUser(id);
            Boolean bool = categoryService.insertCategory(category);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_INSERT.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        }
    }

    @GetMapping("/{id}")
    public Result queryCateById(@PathVariable String id){
        try {
            Category category = categoryService.selectCategoryById(id);
            return Result.success(category);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @PutMapping
    public Result editCategory(HttpServletRequest req,@RequestBody Category category){
        try {
            String id =(String) req.getSession().getAttribute("Employee");
            category.setUpdateUser(id);
            Boolean bool = categoryService.editCategory(category);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }

    @DeleteMapping
    public Result deleteCategory(@RequestParam("ids") String id){
        try {
            Boolean bool = categoryService.deleteCategory(id);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_DELETE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        }
    }

    @GetMapping("/list")
    public Result queryCategoryList(@RequestParam("type") String type){
        try {
            List<Category> categoryList = categoryService.selectAllCategoryByType(type);
            return Result.success(categoryList);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }
}
