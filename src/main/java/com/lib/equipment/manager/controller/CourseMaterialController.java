package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.CourseMaterialDTO;
import com.lib.equipment.manager.dto.CourseMaterialResDTO;
import com.lib.equipment.manager.dto.ResultDTO;
import com.lib.equipment.manager.dto.StatusMsg;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.model.Course;
import com.lib.equipment.manager.model.CourseMatrial;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.service.CourseMaterialService;
import com.lib.equipment.manager.service.CourseService;
import com.lib.equipment.manager.service.MaterialSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/coursematerial")
public class CourseMaterialController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private MaterialSevice materialSevice;
    @Autowired
    private CourseMaterialService courseMaterialService;


    @PostMapping("deleteOne")
    @ResponseBody
    public Object delete(@RequestBody CourseMatrial courseMatrial){
        if(courseMatrial!=null&&!"".equals(courseMatrial)){
            CourseMatrial courseMatrial1= courseMaterialService.selectById(courseMatrial.getId());
            if(courseMatrial!=null){

                int i= courseMaterialService.delete(courseMatrial1);
                if(i!=0){
                    return new StatusMsg(1,"ok");
                }else {
                    return ResultDTO.errorOf(CustomizeErrorCode.Object_Not_Found);
                }
            }
            return new StatusMsg(0,"fail");
        }
        return new StatusMsg(0,"fail");
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(@Valid @RequestBody CourseMaterialDTO courseMaterialDTO, BindingResult result){

        if(result.hasErrors()){
            System.out.println("err");
            StatusMsg statusMsg = new StatusMsg(0,"数据错误");
            return statusMsg;
        }
        try {
            courseMaterialService.insertDetail(courseMaterialDTO);
            StatusMsg statusMsg = new StatusMsg(1,"添加成功");
            return statusMsg;
        }catch (Exception e){
            StatusMsg statusMsg = new StatusMsg(0,"添加失败");
            return statusMsg;

        }

    }

    @RequestMapping("/list")
    public String list(Model model){

        List<Course>courses= courseService.selectAllCourse();
        if(courses!=null&&courses.size()!=0){
            model.addAttribute("courses",courses);
        }else {
            throw new CustomizeException(CustomizeErrorCode.Course_Not_Found);
        }
        List<Material>materials= materialSevice.selectAllMaterial();
        if(materials!=null&&materials.size()!=0){
            model.addAttribute("materials",materials);
        }else {
            throw new CustomizeException(CustomizeErrorCode.Course_Not_Found);
        }
        List<CourseMaterialResDTO>courseMatrials= courseMaterialService.selectAll();
        if(courseMatrials!=null&&courseMatrials.size()!=0){
            model.addAttribute("details",courseMatrials);

        }

        return "university/per";
    }
}
