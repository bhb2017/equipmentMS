package com.lib.equipment.manager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CourseMaterialResDTO {
    private Long id;
    private String courseName;
    private String courseNo;
    private String materialName;
    private Integer per;

//    private String needTime;//不再使用
    private String specification;
}
