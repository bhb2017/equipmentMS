package com.lib.equipment.manager.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lib.equipment.manager.dto.ForecastDTO;
import com.lib.equipment.manager.dto.ForecastTempDTO;
import com.lib.equipment.manager.excelDate.MaterialExcel;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.CalculateMapper;
import com.lib.equipment.manager.model.CoursePlan;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.MaterialExample;
import com.lib.equipment.manager.service.CourseMaterialService;
import com.lib.equipment.manager.service.CoursePlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/calculate")
public class CalculateController {
    @Autowired
    private CoursePlanService coursePlanService;
    @Autowired
    private CourseMaterialService courseMaterialService;
    @Autowired
    private CalculateMapper calculateMapper;

    @RequestMapping("/export")
    public void export(HttpServletResponse response,HttpServletRequest request){
        ExcelWriter writer = null;
        OutputStream out = null;
        try {


//            ExcelUtils.exportExcel("器材汇总表格","器材",response,materials,MaterialExcel.class);

            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            String fileName = "器材汇总表格";
            Sheet sheet = new Sheet(1, 0, ForecastDTO.class);
            sheet.setSheetName("预购");
            writer.write((List) request.getSession().getAttribute("forecastDTOS"), sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("器材预购汇总表格" + ".xlsx").getBytes(), "ISO8859-1"));
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

    @RequestMapping(value = "/deal",method = RequestMethod.POST)
    @ResponseBody
    public Object deal(){


        return null;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String index(Model model){
        List<CoursePlan> coursePlans= coursePlanService.selectAll();
        if(coursePlans!=null&&coursePlans.size()!=0){
            model.addAttribute("schooltimes",coursePlans);
        }else {
            throw new CustomizeException(CustomizeErrorCode.Course_Plan_Not_Found);
        }
        return "university/holiday";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public String getData(@RequestBody @RequestParam("cpid")String schoolTime,
                          Model model, HttpServletRequest request){

        List<ForecastTempDTO> forecastTempDTOS = calculateMapper.selectByDate(schoolTime);
        System.out.println(forecastTempDTOS);
        List<ForecastDTO>forecastDTOS =new ArrayList<>();
        for (ForecastTempDTO forecastTempDTO : forecastTempDTOS) {
            ForecastDTO forecastDTO = new ForecastDTO();
            forecastDTO.setMaterialName(forecastTempDTO.getMaterialName());
            forecastDTO.setPrice(forecastTempDTO.getPrice());
            forecastDTO.setSpecification(forecastTempDTO.getSpecification());
            forecastDTO.setStorageNum(forecastTempDTO.getStorageNum());
            forecastDTO.setNeedNum(0);
            if(forecastDTOS.isEmpty()){
                forecastDTO.setNeedNum(forecastTempDTO.getNeedNum());
                forecastDTOS.add(forecastDTO);
            }else {
                boolean flag =false;
                for (ForecastDTO dto : forecastDTOS) {
                    if(dto.getMaterialName().equals(forecastDTO.getMaterialName())&&dto.getSpecification().equals(forecastDTO.getSpecification())){
                        dto.setNeedNum(dto.getNeedNum()+forecastTempDTO.getNeedNum());
                        flag=true;
                    }
                }
                if(!flag){
                    forecastDTO.setNeedNum(forecastTempDTO.getNeedNum());
                    forecastDTOS.add(forecastDTO);
                }

            }
        }
        System.out.println(forecastDTOS);
        request.getSession().setAttribute("forecastDTOS",forecastDTOS);
//        model.addAttribute("",forecastDTOS);
        return "redirect:/calculate/list";
    }
}
