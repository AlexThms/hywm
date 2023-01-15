package com.example.hywm.service;

import com.example.hywm.entity.Category;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 15:37
 **/
public interface CategoryService {
    public PageResult selectCategoryPage(PageReqVo pageReqVo) throws Exception;

    public Category selectCategoryById(String id) throws Exception;

    public Boolean insertCategory(Category category) throws Exception;

    public Boolean editCategory(Category category) throws Exception;

    public Boolean deleteCategory(String id) throws Exception;
}
