package com.lib.equipment.manager.service;

import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.CourseMapper;
import com.lib.equipment.manager.model.Course;
import com.lib.equipment.manager.model.CourseExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public List<Course> selectAllCourse() {
        try {
            CourseExample courseExample =new CourseExample();
            courseExample.setOrderByClause("id desc");
            List<Course> courses = courseMapper.selectByExample(courseExample);
            return courses;
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);
        }

    }
}
