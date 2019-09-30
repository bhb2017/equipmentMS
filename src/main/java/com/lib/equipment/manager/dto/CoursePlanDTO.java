package com.lib.equipment.manager.dto;

import com.lib.equipment.manager.model.Course;
import lombok.Data;

import java.util.Date;
@Data
public class CoursePlanDTO {
    private Integer id;
    private Integer num;
    private String schoolTime;
    private Course course;
    private String mark;
}
