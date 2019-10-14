package com.lib.equipment.manager.excelDate;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class MaterialExcel extends BaseRowModel {


    @ExcelProperty(value = {"器材汇总汇总表","器材名"},index = 0)
    private String name;
    @ExcelProperty(value = {"器材汇总汇总表","器材规格"},index = 1)
    private String specification;
    @ExcelProperty(value = {"器材汇总汇总表","单价"},index = 2)
    private Float price;
    @ExcelProperty(value = {"器材汇总汇总表","损耗率"},index = 3)
    private Float rate;

    //    private String needTime;//不再使用
    @ExcelProperty(value = {"器材汇总汇总表","备注"},index = 4)
    private String remark;
}
