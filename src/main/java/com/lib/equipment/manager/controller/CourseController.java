package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.*;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.CourseMapper;
import com.lib.equipment.manager.mapper.CoursePlanMapper;
import com.lib.equipment.manager.model.Course;
import com.lib.equipment.manager.model.CoursePlan;
import com.lib.equipment.manager.model.CoursePlanExample;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private CourseService courseService;


    @RequestMapping("/edit/{id}")
    public String editView(@PathVariable("id") Integer id,Model model){
        if(id!=null&&!"".equals(id)){
            CoursePlan coursePlan = coursePlanMapper.selectByPrimaryKey(id);
            CoursePlanDTO coursePlanDTO = new CoursePlanDTO();
            BeanUtils.copyProperties(coursePlan,coursePlanDTO);
            Course course = courseMapper.selectByPrimaryKey(coursePlan.getCourseId());
            coursePlanDTO.setCourse(course);
            model.addAttribute("coursePlanDTO",coursePlanDTO);
        }
        return "university/edit";
    }

    @PostMapping("/deleteOne")
    @ResponseBody
    public Object deleteOne(@RequestBody CoursePlan coursePlan){
        coursePlan = coursePlanMapper.selectByPrimaryKey(coursePlan.getId());
        if(coursePlan!=null&&!"".equals(coursePlan)){
            int i = coursePlanMapper.deleteByPrimaryKey(coursePlan.getId());
            if(i!=0){
                return new StatusMsg(1,"ok");
            }else{
                return ResultDTO.errorOf((CustomizeErrorCode.Object_Not_Found));
            }
        }
        return new StatusMsg(0,"fail");
    }

    @PostMapping("/updatecourse")
    public  String updatecourse(@Valid Course course ,UpdateCourse updateCourse, Model model){
        try{
            course = courseMapper.selectByPrimaryKey(course.getId());
            course.setCourseName(updateCourse.getNewcourseName());
            course.setCourseNo(updateCourse.getNewcourseNo());
            courseMapper.updateByPrimaryKey(course);
            return "redirect:/course/list";
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }
    }

    @PostMapping("/update")
    public String courseplanupdate(UpdateCoursePlan updateCoursePlan, Model model){
        try{

            Course course = new Course();
            Integer coursePlanId = updateCoursePlan.getCoursePlanId();
            CoursePlan coursePlan = coursePlanMapper.selectByPrimaryKey(coursePlanId);
            coursePlan.setSchoolTime(updateCoursePlan.getNewschoolTime());
            coursePlan.setNum(updateCoursePlan.getNewnum());
            coursePlan.setMark(updateCoursePlan.getNewmark());
            coursePlanMapper.updateByPrimaryKey(coursePlan);
            Integer courseId = coursePlan.getCourseId();
            course = courseMapper.selectByPrimaryKey(courseId);
            course.setCourseNo(updateCoursePlan.getNewcourseNo());
            course.setCourseName(updateCoursePlan.getNewCourseNname());
            courseMapper.updateByPrimaryKey(course);
            return "redirect:/course/list";
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }
    }

    @GetMapping("/list")
    public String coursePlan(@ModelAttribute("coursePlan") CoursePlan coursePlan, Model model){
        try{
            list(model);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "university/courses";
    }

    private void  list(Model model){
        CoursePlanExample coursePlanExample= new CoursePlanExample();
        coursePlanExample.setOrderByClause("id desc");
        List<CoursePlan> coursePlans =coursePlanMapper.selectByExample(coursePlanExample);
        List<CoursePlanDTO> coursePlanDTOS = new ArrayList<>();
        log.info("coursePlans list:",coursePlans.toArray());
        for(CoursePlan coursePlan : coursePlans){
            Course course = courseMapper.selectByPrimaryKey(coursePlan.getCourseId());
            CoursePlanDTO coursePlanDTO = new CoursePlanDTO();
            BeanUtils.copyProperties(coursePlan,coursePlanDTO);
            coursePlanDTO.setCourse(course);
            coursePlanDTOS.add(coursePlanDTO);
        }

        List<Course> courses = courseService.selectAllCourse();
        model.addAttribute("courses",courses);
        model.addAttribute("coursePlans",coursePlanDTOS);


    }

    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public  CoursePlan coursePlanDetail(@RequestBody @RequestParam("mid")Integer id){
        ModelAndView mv = new ModelAndView();
        if(id!=null&&!"".equals(id)){
            CoursePlan coursePlan = coursePlanMapper.selectByPrimaryKey(id);
            if(coursePlan!=null){
                mv.setViewName("university/course");
                return coursePlan;
            }else {
                throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
            }
        }else {
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }
    }

    @RequestMapping("/addCoursePlan")
    public String coursePlanAdd(@Valid CoursePlan coursePlan, Model model, BindingResult result){
        if(result.hasErrors()){
            System.out.println("err");
            return "redirect:/course/list";
        }
        if(coursePlan!=null){
            coursePlanMapper.insertSelective(coursePlan);
        }
        list(model);
        return "university/courses";
    }

    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public String courseAdd(@Valid Course course, Model model, BindingResult result){
        if(result.hasErrors()){
            System.out.println("err");
            return "redirect:/course/list";
        }
        if(course!=null){
            courseMapper.insertSelective(course);
        }
        list(model);
        return "redirect:/university/courses";
    }


}
