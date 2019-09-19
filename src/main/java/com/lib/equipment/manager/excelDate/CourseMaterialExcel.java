package com.lib.equipment.manager.excelDate;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class CourseMaterialExcel extends BaseRowModel {
    @ExcelProperty(value = "课程名",index = 0)
    private String courseName;
    @ExcelProperty(value = "器材编号",index = 1)
    private String courseNo;
    @ExcelProperty(value = "器材名",index = 2)
    private String materialName;
    @ExcelProperty(value = "每人所需",index = 3)
    private Integer per;

    //    private String needTime;//不再使用
    @ExcelProperty(value = "规格",index = 4)
    private String specification;
}
