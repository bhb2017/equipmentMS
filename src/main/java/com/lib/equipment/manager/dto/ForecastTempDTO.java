package com.lib.equipment.manager.dto;

import lombok.Data;

@Data
public class ForecastTempDTO {
    private Long id;
    private String materialName;
    private String specification;
    private Float price;
    private Integer courseNum;//上课人数
    private Integer per;//每人所需数
    private Integer storageNum;//存储数
    private Float rate;
    private Integer needNum;
}
