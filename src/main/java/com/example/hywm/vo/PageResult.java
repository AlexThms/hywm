package com.example.hywm.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult{
    /** 当前页码 */
    private int pageNum;
    /** 每页数量 */
    private int pageSize;
    /** 记录总数 */
    private long total;
    /** 页码总数 */
    private int Pages;
    /** 数据模型*/
    private List<?> records;
}
