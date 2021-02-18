package com.lib.equipment.manager.service;

import com.lib.equipment.manager.dto.CourseMaterialDTO;
import com.lib.equipment.manager.dto.CourseMaterialResDTO;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.CourseMapper;
import com.lib.equipment.manager.mapper.CourseMatrialMapper;
import com.lib.equipment.manager.mapper.CoursePlanMapper;
import com.lib.equipment.manager.mapper.MaterialMapper;
import com.lib.equipment.manager.model.CourseExample;
import com.lib.equipment.manager.model.CourseMatrial;
import com.lib.equipment.manager.model.CourseMatrialExample;
import com.lib.equipment.manager.model.CoursePlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CourseMaterialService {
    @Autowired
    private CourseMatrialMapper courseMatrialMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;

    public void insertDetail(CourseMaterialDTO courseMaterialDTO) throws ParseException {
        CourseMatrial courseMatrial = new CourseMatrial();
//        String needTime = courseMaterialDTO.getNeedTime();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date needtime=dateFormat.parse(needTime);

        BeanUtils.copyProperties(courseMaterialDTO,courseMatrial);
        try {
//            courseMatrial.setNeedTime(needtime);
            courseMatrial.setCourseId(courseMaterialDTO.getCid());
            courseMatrial.setMaterialId(courseMaterialDTO.getMid());

            int i = courseMatrialMapper.insertSelective(courseMatrial);
        }catch (Exception e){
            log.error("insertDetail",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);
        }

    }

    public List<CourseMaterialResDTO> selectAll() {
        CourseMatrialExample courseMatrialExample = new CourseMatrialExample();
        try {

            courseMatrialExample.setOrderByClause("id desc");
            List<CourseMatrial> courseMatrials = courseMatrialMapper.selectByExample(courseMatrialExample);
            List<CourseMaterialResDTO>courseMaterialResDTOS = new ArrayList<>();
            for (CourseMatrial courseMatrial : courseMatrials) {
                CourseMaterialResDTO courseMaterialResDTO=new CourseMaterialResDTO();
                BeanUtils.copyProperties(courseMatrial,courseMaterialResDTO);
                String courseName = courseMapper.selectByPrimaryKey(courseMatrial.getCourseId()).getCourseName();
                courseMaterialResDTO.setCourseName(courseName);
                String courseNo= courseMapper.selectByPrimaryKey(courseMatrial.getCourseId()).getCourseNo();
                courseMaterialResDTO.setCourseNo(courseNo);
                String materilName = materialMapper.selectByPrimaryKey(courseMatrial.getMaterialId()).getName();
                String specification= materialMapper.selectByPrimaryKey(courseMatrial.getMaterialId()).getSpecification();
                courseMaterialResDTO.setMaterialName(materilName);
                courseMaterialResDTO.setSpecification(specification);

//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String dateString = simpleDateFormat.format(courseMatrial.getNeedTime());
//                courseMaterialResDTO.setNeedTime(dateString);
                courseMaterialResDTOS.add(courseMaterialResDTO);
            }
            return courseMaterialResDTOS;
        }catch (Exception e){
            log.error("selectAll",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);
        }


    }

    public CourseMatrial selectById(Long id) {
        try {
            CourseMatrial courseMatrial = courseMatrialMapper.selectByPrimaryKey(id);
            return courseMatrial;
        }catch (Exception e){
            log.error("selectById",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);

        }

    }

    public int delete(CourseMatrial courseMatrial1) {
        try {
            int i = courseMatrialMapper.deleteByPrimaryKey(courseMatrial1.getId());
            return i;
        }catch (Exception e){
            log.error("delete",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

        }

    }

    public List<CourseMatrial> selectByCourseId(Integer courseId) {
        try {
            CourseMatrialExample courseMatrialExample = new CourseMatrialExample();
            CourseMatrialExample.Criteria criteria = courseMatrialExample.createCriteria();
            criteria.andCourseIdEqualTo(courseId);
            List<CourseMatrial> courseMatrials = courseMatrialMapper.selectByExample(courseMatrialExample);
            return courseMatrials;
        }catch (Exception e){
            log.error("selectByCourseId",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

        }

    }

    public void add(CoursePlan coursePlan, Integer[] materIds) {
        for (Integer materId : materIds) {
            coursePlanMapper.insert(coursePlan);
            Integer coursePlanId = coursePlan.getId();
            CourseMatrial courseMatrial = new CourseMatrial();
            courseMatrial.setMaterialId(Long.valueOf(materId));
            courseMatrial.setCourseId(coursePlan.getCourseId());
            courseMatrialMapper.insert(courseMatrial);
        }



    }
}
