package com.lib.equipment.manager.model;

import lombok.Data;

@Data
public class Storage {
    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", materialId=" + materialId +
                ", num=" + num +
                ", place='" + place + '\'' +
                '}';
    }

    private Long id;


    private Long materialId;


    private Integer num;


    private String place;


}