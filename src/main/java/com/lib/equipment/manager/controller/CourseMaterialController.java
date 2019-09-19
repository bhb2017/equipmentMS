package com.lib.equipment.manager.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lib.equipment.manager.dto.CourseMaterialDTO;
import com.lib.equipment.manager.dto.CourseMaterialResDTO;
import com.lib.equipment.manager.dto.ResultDTO;
import com.lib.equipment.manager.dto.StatusMsg;
import com.lib.equipment.manager.excelDate.CourseMaterialExcel;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.model.Course;
import com.lib.equipment.manager.model.CourseMatrial;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.MaterialExample;
import com.lib.equipment.manager.service.CourseMaterialService;
import com.lib.equipment.manager.service.CourseService;
import com.lib.equipment.manager.service.MaterialSevice;
import com.lib.equipment.manager.utils.ExcelUtils;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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


    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        ExcelWriter writer = null;
        OutputStream out = null;
        try {

            List<CourseMaterialResDTO> courseMaterialResDTOS= courseMaterialService.selectAll();
            List<CourseMaterialExcel> courseMaterialExcels = new ArrayList<>();
            for (CourseMaterialResDTO courseMaterialResDTO : courseMaterialResDTOS) {
                CourseMaterialExcel courseMaterialExcel = new CourseMaterialExcel();
                BeanUtils.copyProperties(courseMaterialResDTO,courseMaterialExcel);
                courseMaterialExcels.add(courseMaterialExcel);
            }
//            ExcelUtils.exportExcel("课程所需详情表","课程所需详情",response,courseMaterialExcels,CourseMaterialExcel.class);
            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            String fileName = "器材汇总表格";
            Sheet sheet = new Sheet(1, 0, CourseMaterialExcel.class);
            sheet.setSheetName("课程所需详情");
            writer.write(courseMaterialExcels, sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("课程所需详情表" + ".xlsx").getBytes(), "ISO8859-1"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.finish();
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
