package com.lib.equipment.manager.dto;

import com.lib.equipment.manager.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserData {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private Integer status;
    private Role roles;
}
