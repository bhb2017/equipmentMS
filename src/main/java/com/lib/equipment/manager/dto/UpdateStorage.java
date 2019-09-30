package com.lib.equipment.manager.dto;
import lombok.Data;

@Data
public class UpdateStorage {
    private Long newid;
    private Integer newnum;
    private String newplace;
    private Long materialid;
}
