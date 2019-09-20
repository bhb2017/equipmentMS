package com.lib.equipment.manager.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

@Data
public class ForecastDTO extends BaseRowModel {
//    private Long id;
    @ExcelProperty(value = "器材名",index = 0)
    private String materialName;
    @ExcelProperty(value = "规格",index = 1)
    private String specification;
    @ExcelProperty(value = "单价",index = 2)
    private Float price;
//    private Integer courseNum;//上课人数
//    private Integer per;//每人所需数
    @ExcelProperty(value = "存储数",index = 3)
    private Integer storageNum;//存储数
//    private Float rate;
    @ExcelProperty(value = "所需数",index = 4)
    private Integer needNum;
}
