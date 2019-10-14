package com.lib.equipment.manager.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
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
import com.lib.equipment.manager.service.MaterialSevice;
import com.lib.equipment.manager.utils.ExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/material")
public class MaterialController  {

    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private MaterialSevice materialSevice;


    @RequestMapping("/upload")
    public String upload(@RequestParam("filename") MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelListener listener = new ExcelListener();
        ExcelReader excelReader = new ExcelReader(inputStream,ExcelTypeEnum.XLSX,null,listener);
        excelReader.read(new Sheet(1,2,MaterialExcel.class));
        List<Object> datas = listener.getDatas();
//        List<Material>materials = new ArrayList<>();
        Material material = new Material();
        try {
            for (Object data : datas) {
                System.out.println(data);
                BeanUtils.copyProperties(data,material);
                materialSevice.insertMaterial(material);
            }
        }catch (Exception e){
            log.error("excel上传失败：{}",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);

        }


        return "redirect:/material/list";
    }

//器材汇总表格导出下载


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

            return "redirect:/material/list";
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
              Material dbMaterial= materialSevice.selectByNameAndSpecification(material);
              if(dbMaterial==null){
                  materialSevice.insertMaterial(material);
              }else {

              }
//              materialMapper.insertSelective(material);
          }

          return "redirect:/material/list";

    }
}
