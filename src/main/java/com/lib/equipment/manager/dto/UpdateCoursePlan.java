package com.lib.equipment.manager.dto;

import com.lib.equipment.manager.model.Course;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateCoursePlan {
    private String newCourseNname;
    private Integer coursePlanId;
    private Integer newnum;
    private String newschoolTime;
    private String newcourseNo;
    private String newmark;
}
