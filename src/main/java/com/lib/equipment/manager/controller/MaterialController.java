package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.StatusMsg;
import com.lib.equipment.manager.dto.UpdateMaterial;
import com.lib.equipment.manager.mapper.MaterialMapper;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.MaterialExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialMapper materialMapper;

    @PostMapping("/deleteOne")
    @ResponseBody
    public Object deleteOne(@RequestBody Material material){
        if(material!=null&&!"".equals(material)){
            int i = materialMapper.deleteByPrimaryKey(material.getId());
            if(i!=0){
                return new StatusMsg(1,"ok");
            }
        }
        return new StatusMsg(1,"ok");
    }

    @PostMapping("/update")
    public String materialupdate(UpdateMaterial updateMaterial,Model model){
        Material material = new Material();
        material.setId(updateMaterial.getNewid());
        material.setPrice(updateMaterial.getNewprice());
        material.setRate(updateMaterial.getNewrate());
        material.setSpecification(updateMaterial.getNewspecification());
        material.setRemark(updateMaterial.getNewremark());
        System.out.println(updateMaterial);
        materialMapper.updateByPrimaryKeySelective(material);
        list(model);
        return "university/material";
    }

    @ResponseBody
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    public Material materialDetail(@RequestBody @RequestParam("mid")Long id){
        ModelAndView mv = new ModelAndView();
        if(id!=null&&!"".equals(id)){
            Material material = materialMapper.selectByPrimaryKey(id);
            mv.setViewName("university/material");
            return material;
        }
        mv.setViewName("university/material");
        return null;
    }

    @GetMapping("/list")
    public String material(Model model){
        list(model);
        return "university/material";
    }

    private void list(Model model) {
        MaterialExample materialExample =new MaterialExample();
        materialExample.setOrderByClause("id desc");
        List<Material> materials = materialMapper.selectByExample(materialExample);
        log.info("materials list:",materials.toArray());
        model.addAttribute("materials",materials);
    }

    @PostMapping("/add")
    public String materialAdd(Material material,Model model){
      log.info("add material:",material.toString());
        if(material!=null){
            materialMapper.insertSelective(material);
        }
        list(model);
        return "university/material";
    }
}
