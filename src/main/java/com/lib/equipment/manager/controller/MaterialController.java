package com.lib.equipment.manager.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lib.equipment.manager.dto.ResultDTO;
import com.lib.equipment.manager.dto.StatusMsg;
import com.lib.equipment.manager.dto.UpdateMaterial;
import com.lib.equipment.manager.excelDate.MaterialExcel;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.MaterialMapper;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.MaterialExample;
import com.lib.equipment.manager.model.User;
import com.lib.equipment.manager.utils.DemoDataListener;
import com.lib.equipment.manager.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/material")
public class MaterialController  {

    @Autowired
    private MaterialMapper materialMapper;

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file= multipartRequest.getFile("filename");

        if(file.isEmpty()){
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }else {
            try {

                String filePath= file.getOriginalFilename();

                File file1 = new File(filePath);
//                file.transferTo(file1);
                String absolutePath = file1.getAbsolutePath();

                EasyExcel.read(absolutePath, Material.class, new DemoDataListener()).sheet().doRead();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return "redirect:/material/list";
    }

//器材汇总表格导出下载

    @RequestMapping("/read")
    public void read(){
        String fileName="C:\\Users\\cxq\\Desktop\\器材汇总表格.xlsx";
        EasyExcel.read(fileName, Material.class, new DemoDataListener()).sheet().doRead();
    }

    @RequestMapping("/download")
    public void downloadExcel(Model model, HttpServletResponse response) throws Exception {
        ExcelWriter writer = null;
        OutputStream out = null;
        try {
            MaterialExample materialExample = new MaterialExample();
            materialExample.setOrderByClause("id desc");
            List<Material> materials = materialMapper.selectByExample(materialExample);
            List<MaterialExcel>materialExcels = new ArrayList<>();
            for (Material material : materials) {

                MaterialExcel materialExcel =new MaterialExcel();
                BeanUtils.copyProperties(material,materialExcel);
                materialExcels.add(materialExcel);

            }

//            ExcelUtils.exportExcel("器材汇总表格","器材",response,materials,MaterialExcel.class);

            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            String fileName = "器材汇总表格";
            Sheet sheet = new Sheet(1, 0, MaterialExcel.class);
            sheet.setSheetName("器材");
            writer.write(materialExcels, sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("器材汇总表格" + ".xlsx").getBytes(), "ISO8859-1"));
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



    /*
    * post请求
    * 器材删除
    * 前端ajax传递id用对象接收
    * 根据id删除
    * 删除失败抛出异常，全局异常接收
    * */
    @PostMapping("/deleteOne")
    @ResponseBody
    public Object deleteOne(@RequestBody Material material){
        if(material!=null&&!"".equals(material)){
            int i = materialMapper.deleteByPrimaryKey(material.getId());
            if(i!=0){
                return new StatusMsg(1,"ok");
            }else {
                return ResultDTO.errorOf(CustomizeErrorCode.Object_Not_Found);
            }
        }
        return new StatusMsg(0,"fail");
    }
    /*
    * 更新器材
    * 前端接收UpdateMaterial对象
    * */
    @PostMapping("/update")
    public String materialupdate(UpdateMaterial updateMaterial,Model model){
            try {
            Material material = new Material();
            material.setId(updateMaterial.getNewid());
            material.setPrice(updateMaterial.getNewprice());
            material.setRate(updateMaterial.getNewrate());
            material.setSpecification(updateMaterial.getNewspecification());
            material.setRemark(updateMaterial.getNewremark());
            log.info("material:",material);
            materialMapper.updateByPrimaryKeySelective(material);
            list(model);
            return "university/material";
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);

        }

    }
    /*
    *  接收器材id mid
    *  根据id查询具体的material
    *  前端是一个ajax异步请求
    *  返回一个Material对象
    * */
    @ResponseBody
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    public Material materialDetail(@RequestBody @RequestParam("mid")Long id){
        ModelAndView mv = new ModelAndView();
        if(id!=null&&!"".equals(id)){
            Material material = materialMapper.selectByPrimaryKey(id);
            if(material!=null){
                mv.setViewName("university/material");
                return material;
            }else {
                throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
            }
        }else {
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }
//        mv.setViewName("university/material");

    }

    /**
     *
     * @param material
     * @param model
     * @return
     * 传递model参数
     * 调用list方法
     */
    @GetMapping("/list")
    public String material(@ModelAttribute("material") Material material,Model model){
        try {
            list(model);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "university/material";
    }

    /**
     *
     * @param model
     * 根据id降序
     * 获取material list
     */
    private void list(Model model) {
        MaterialExample materialExample =new MaterialExample();
        materialExample.setOrderByClause("id desc");
        List<Material> materials = materialMapper.selectByExample(materialExample);
        log.info("materials list:",materials.toArray());
        model.addAttribute("materials",materials);
    }

    /**
     * 添加器材
     * 增加表单验证@Valid
     * @param material
     * @param model
     * @param result 表单验证的参数
     * @return
     */
    @RequestMapping("/add")
    public String materialAdd(@Valid Material material, Model model,
                              BindingResult result){

          if(result.hasErrors()){
              System.out.println("err");

              return "redirect:/material/list";
          }
          if(material!=null){
              materialMapper.insertSelective(material);
          }
          list(model);
          return "university/material";

    }
}
