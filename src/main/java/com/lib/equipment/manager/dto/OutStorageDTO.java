package com.lib.equipment.manager.dto;

import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class OutStorageDTO {
    private Long id;
    private String outTime;
    private String remark;
    private Integer num;
    private Material material;
    private String user;
}
