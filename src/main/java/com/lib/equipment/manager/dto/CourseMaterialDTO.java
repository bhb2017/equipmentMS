package com.lib.equipment.manager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CourseMaterialDTO {
    @NotNull(message = "不能为空")
    private Integer cid;
    @NotNull(message = "不能为空")
    private Long mid;
    @NotNull(message = "不能为空")
    private Integer per;

    private String needTime;
}
