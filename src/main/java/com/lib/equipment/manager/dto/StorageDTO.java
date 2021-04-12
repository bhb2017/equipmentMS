package com.lib.equipment.manager.dto;

import com.lib.equipment.manager.model.Material;
import lombok.Data;

@Data
public class StorageDTO {
    private Long id;


    private Material material;

    @Override
    public String toString() {
        return "StorageDTO{" +
                "id=" + id +
                ", material=" + material +
                ", num=" + num +
                ", place='" + place + '\'' +
                '}';
    }

    private Integer num;


    private String place;
}
