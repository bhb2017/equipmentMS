package com.lib.equipment.manager.dto;

import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class InStorageDTO {

    private Long id;
    private String inTime;
    private String remark;
    private Integer num;
    private Material material;
    private String user;
}
