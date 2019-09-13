package com.lib.equipment.manager.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
@Data
public class ExcelReadMaterial{

    @ExcelProperty(value = "器材名")
    private String name;


    @ExcelProperty(value = "规格")
    private String specification;

    @ExcelProperty(value = "单价")
    private Float price;

    @ExcelProperty(value = "损耗率")
    private Float rate;

    @ExcelProperty(value = "备注")
    private String remark;
}
