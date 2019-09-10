package com.lib.equipment.manager.dto;

import com.lib.equipment.manager.model.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UpdateUser {
    private Integer id;
    @NotNull
    @Size(min = 2,max = 30)
    private String username;
    @NotNull
    @Size(min = 3,max = 20)
    private String password;

    @Size(min = 11,max = 11)
    private String phone;
    private Integer status;
    private List<Integer> roles;
}
