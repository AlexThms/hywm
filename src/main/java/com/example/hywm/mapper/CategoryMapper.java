package com.example.hywm.mapper;

import com.example.hywm.entity.Category;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 15:38
 **/
@Repository
public interface CategoryMapper {
    /**
     * 查询全部
     *
     * @return
     */
    List<Category> selectAllCategory();

    /**
     * 查询通过id
     * @return
     */
    Category selectCategoryById(String id);

    /**
     * 添加菜品分类
     * @param category
     * @return
     */
    Integer insertCategory(Category category);

    /**
     * 修改菜品分类
     * @param category
     * @return
     */
    Integer editCategory(Category category);

    /**
     * 删除菜品分类
     * @param id
     * @return
     */
    Integer deleteCategory(String id);
}
