package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.InStorageDTO;
import com.lib.equipment.manager.dto.OutStorageDTO;
import com.lib.equipment.manager.dto.ResultDTO;
import com.lib.equipment.manager.dto.StatusMsg;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.mapper.InStorageMapper;
import com.lib.equipment.manager.mapper.MaterialMapper;
import com.lib.equipment.manager.mapper.OutStorageMapper;
import com.lib.equipment.manager.mapper.UserMapper;
import com.lib.equipment.manager.model.*;
import com.lib.equipment.manager.service.MaterialSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/storagerecord")
public class StorageRecordController {
    @Autowired
    private InStorageMapper inStorageMapper;
    @Autowired
    private OutStorageMapper outStorageMapper;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private MaterialSevice materialSevice;


    @PostMapping("/deleteIn")
    @ResponseBody
    public Object deleteIn(@RequestBody InStorage inStorage){
        if(inStorage!=null&&!"".equals(inStorage)){
            int i = inStorageMapper.deleteByPrimaryKey(inStorage.getId());
            if(i!=0){
                return new StatusMsg(1,"ok");
            }
            else {
                return ResultDTO.errorOf(CustomizeErrorCode.Object_Not_Found);
            }
        }
        return new StatusMsg(0,"fail");
    }



    @GetMapping("/list")
    public String instorage(@ModelAttribute("instorage") InStorage inStorage, Model model,
                            HttpServletRequest request){
        try{
            list(model,request);
            listout(model,request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "university/storagerecord";
    }


    private void list(Model model,HttpServletRequest request){
        InStorageExample inStorageExample= new InStorageExample();
        inStorageExample.setOrderByClause("id desc");
        List<InStorage> inStorageList = inStorageMapper.selectByExample(inStorageExample);
        List<InStorageDTO> inStorageS = new ArrayList<>();
        log.info("instorages list:",inStorageS.toArray());
        for (InStorage inStorage: inStorageList){
            Material material = materialMapper.selectByPrimaryKey(inStorage.getMaterialId());
            InStorageDTO inStorageDTO = new InStorageDTO();
            BeanUtils.copyProperties(inStorage,inStorageDTO);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(inStorage.getInTime());
            inStorageDTO.setInTime(dateString);
            inStorageDTO.setMaterial(material);
            String user = (String) request.getSession().getAttribute("user");
            inStorageDTO.setUser(user);
            inStorageS.add(inStorageDTO);
        }
        List<Material> materials = materialSevice.selectAllMaterial();
        model.addAttribute("materials",materials);
        model.addAttribute("instorages",inStorageS);
    }


//OutStorageRecord---------------------------------------------------------------------

    @PostMapping("/deleteOut")
    @ResponseBody
    public Object deleteOut(@RequestBody OutStorage outStorage){
        if(outStorage != null&&!"".equals(outStorage)){
            int i = outStorageMapper.deleteByPrimaryKey(outStorage.getId());
            if(i!=0){
                return new StatusMsg(1,"ok");
            }
            else {
                return ResultDTO.errorOf(CustomizeErrorCode.Object_Not_Found);
            }
        }
        return new StatusMsg(0,"fail");
    }

//    @GetMapping("/listout")
//    public String outstorage(@ModelAttribute("outstorage") OutStorage outStorage, Model model){
//        try{
//            listout(model,);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "university/storagerecord";
//    }


    private void listout(Model model,HttpServletRequest request){
        OutStorageExample outStorageExample= new OutStorageExample();
        outStorageExample.setOrderByClause("id desc");
        List<OutStorage> outStorageList = outStorageMapper.selectByExample(outStorageExample);
        List<OutStorageDTO> outStorages = new ArrayList<>();
        log.info("outstorages list:",outStorages.toArray());
        for (OutStorage outStorage: outStorageList){
            Material material = materialMapper.selectByPrimaryKey(outStorage.getMaterialId());
            OutStorageDTO outStorageDTO = new OutStorageDTO();
            BeanUtils.copyProperties(outStorage, outStorageDTO);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(outStorage.getOutTime());
            outStorageDTO.setOutTime(dateString);
            outStorageDTO.setMaterial(material);
            String user = (String) request.getSession().getAttribute("user");
            outStorageDTO.setUser(user);
            outStorages.add(outStorageDTO);
        }
        List<Material> materials = materialSevice.selectAllMaterial();
        model.addAttribute("materials",materials);
        model.addAttribute("outstorages",outStorages);
    }






}
