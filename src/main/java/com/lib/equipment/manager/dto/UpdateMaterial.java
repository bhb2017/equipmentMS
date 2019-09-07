package com.lib.equipment.manager.dto;

import lombok.Data;

@Data
public class UpdateMaterial {
    private Long newid;
    private String newspecification;
    private Float newprice;
    private Float newrate;
    private String newremark;

}
