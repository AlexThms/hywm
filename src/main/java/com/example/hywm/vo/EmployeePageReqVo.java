package com.example.hywm.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class EmployeePageReqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int page;

    private int PageSize;

    private String name;
}
