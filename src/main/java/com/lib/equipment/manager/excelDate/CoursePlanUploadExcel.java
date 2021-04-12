package com.lib.equipment.manager.excelDate;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class CoursePlanUploadExcel extends BaseRowModel {
    @ExcelProperty(value = {"课程计划","课程名"},index = 0)
    private String courseName;
    @ExcelProperty(value = {"课程计划","课程编号"},index = 1)
    private String courseNo;
    @ExcelProperty(value = {"课程计划","课程人数"},index = 2)
    private Integer num;
    @ExcelProperty(value = {"课程计划","开课时间"},index = 3)
    private String schoolTime;
    @ExcelProperty(value = {"课程计划","备注"},index = 4)
    private String mark;
}
