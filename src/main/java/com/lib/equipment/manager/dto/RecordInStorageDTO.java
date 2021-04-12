package com.lib.equipment.manager.dto;

import com.lib.equipment.manager.model.Material;
import lombok.Data;

@Data
public class RecordInStorageDTO {
    private Long id;
    private String inTime;
    private String remark;
    private Integer num;
    private Material material;
    private String place;
    private String user;
}
