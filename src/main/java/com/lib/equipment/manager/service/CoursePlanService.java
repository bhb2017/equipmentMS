package com.lib.equipment.manager.service;

import com.lib.equipment.manager.dto.ShoolTimesDTO;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.CoursePlanMapper;
import com.lib.equipment.manager.model.CoursePlan;
import com.lib.equipment.manager.model.CoursePlanExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePlanService {
    @Autowired
    private CoursePlanMapper coursePlanMapper;

    public List<ShoolTimesDTO> selectAll() {
        try {
            CoursePlanExample coursePlanExample = new CoursePlanExample();
            coursePlanExample.setOrderByClause("id desc");

//            List<CoursePlan> coursePlans = coursePlanMapper.selectByExample(coursePlanExample);
            List<ShoolTimesDTO>shoolTimesDTOS= coursePlanMapper.selectShoolTime();
            return shoolTimesDTOS;
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

        }

    }

    public List<CoursePlan> selectBySchoolTime(String schoolTime) {
        try {
            CoursePlanExample coursePlanExample = new CoursePlanExample();
            CoursePlanExample.Criteria criteria = coursePlanExample.createCriteria();
            criteria.andSchoolTimeEqualTo(schoolTime);
            List<CoursePlan> coursePlans = coursePlanMapper.selectByExample(coursePlanExample);
            return coursePlans;
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.Select_Error);

        }

    }
}
