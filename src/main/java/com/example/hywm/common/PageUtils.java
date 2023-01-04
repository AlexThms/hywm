package com.example.hywm.common;

import com.example.hywm.vo.PageResult;
import com.github.pagehelper.PageInfo;

public class PageUtils {
    /**
     * 将分页信息封装到统一的接口
     * @param pageInfo
     * @return
     */
    public static PageResult getPageResult(PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setPages(pageInfo.getPages());
        pageResult.setRecords(pageInfo.getList());
        return pageResult;
    }
}
