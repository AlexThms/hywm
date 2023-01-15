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
    PageResult selectCategoryPage(PageReqVo pageReqVo) throws Exception;

    Category selectCategoryById(String id) throws Exception;

    Boolean insertCategory(Category category) throws Exception;

    Boolean editCategory(Category category) throws Exception;

    Boolean deleteCategory(String id) throws Exception;
}
