package com.example.hywm.service.impl;

import com.example.hywm.common.PageUtils;
import com.example.hywm.entity.Category;
import com.example.hywm.mapper.CategoryMapper;
import com.example.hywm.service.CategoryService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 15:38
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public PageResult selectCategoryPage(PageReqVo pageReqVo) throws Exception{
        int page = pageReqVo.getPage();
        int pageSize = pageReqVo.getPageSize();
        PageHelper.startPage(page,pageSize);
        List<Category> categoryList = categoryMapper.selectAllCategory();
        List<Category> collect = categoryList.stream().sorted(Comparator.comparing(Category::getSort)).collect(Collectors.toList());
        PageInfo<Category> pageInfo = new PageInfo<>(collect);
        PageResult pageResult = PageUtils.getPageResult(pageInfo);
        return pageResult;
    }

    @Override
    public Category selectCategoryById(String id) throws Exception {
        Category category = categoryMapper.selectCategoryById(id);
        return category;
    }

    @Override
    public Boolean insertCategory(Category category) throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        category.setId(uuid);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Integer integer = categoryMapper.insertCategory(category);
        if (integer != 1){
            return false;
        }
        return true;
    }

    @Override
    public Boolean editCategory(Category category) throws Exception {
        category.setUpdateTime(LocalDateTime.now());
        Integer integer = categoryMapper.editCategory(category);
        if (integer != 1){
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteCategory(String id) throws Exception {
        Integer integer = categoryMapper.deleteCategory(id);
        if (integer != 1){
            return false;
        }
        return true;
    }

}
