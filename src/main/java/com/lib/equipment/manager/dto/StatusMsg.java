package com.lib.equipment.manager.dto;

import lombok.Data;

@Data
public class StatusMsg {
    private Integer code;
    private String msg;

    public StatusMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
