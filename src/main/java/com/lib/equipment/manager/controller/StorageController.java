package com.lib.equipment.manager.controller;

import com.lib.equipment.manager.dto.*;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.MaterialMapper;
import com.lib.equipment.manager.mapper.StorageMapper;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.Storage;
import com.lib.equipment.manager.model.StorageExample;
import com.lib.equipment.manager.service.MaterialSevice;
import com.lib.equipment.manager.service.StorageService;
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
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private MaterialSevice materialSevice;
    @Autowired
    private StorageService storageService;

//删除
    @PostMapping("/deleteOne")
    @ResponseBody
    public  Object deleteOne(@RequestBody Storage storage){
        if(storage!=null&&!"".equals(storage)){
            int i = storageMapper.deleteByPrimaryKey(storage.getId());
            if(i!=0){
                return  new StatusMsg(1,"ok");
            }else{
                return ResultDTO.errorOf(CustomizeErrorCode.Object_Not_Found);
            }
        }
        return new StatusMsg(0,"fail");
    }
//更新

    /**
     *
     * @param updateStorage
     * @param model
     * @return
     */
    @PostMapping("/update")
    public  String storageupdate(UpdateStorage updateStorage, Model model){
        try{
            Storage storage = new Storage();
            storage.setId(updateStorage.getNewid());
            storage.setPlace(updateStorage.getNewplace());
            storage.setNum(updateStorage.getNewnum());
            storage.setMaterialId(updateStorage.getMaterialid());
            log.info("storage:{}",storage);
            storageMapper.updateByPrimaryKeySelective(storage);
            list(model);
            return "university/storage";
        }catch (Exception e){
            log.error("库存更新失败:{}",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }
    }
//    列表
    @GetMapping("/list")
    public String storage(@ModelAttribute("storage") Storage storage,Model model){
        try{
            list(model);

        }catch (Exception e){
            log.error("storagelist err:{}",e.getMessage());
            e.printStackTrace();
        }
        return "university/storage";
    }
    private  void  list(Model model){
        StorageExample storageExample =new StorageExample();
        storageExample.setOrderByClause("id desc");
        List<Storage> storageList = storageMapper.selectByExample(storageExample);
        List<StorageDTO> storages = new ArrayList<>();
        log.info("storages list:{}",storages.toArray());
        for (Storage storage : storageList) {
            Material material = materialMapper.selectByPrimaryKey(storage.getMaterialId());
            StorageDTO storageDTO = new StorageDTO();
            BeanUtils.copyProperties(storage,storageDTO);
            storageDTO.setMaterial(material);
            storages.add(storageDTO);
        }
        List<Material> materials = materialSevice.selectAllMaterial();
        model.addAttribute("materials",materials);
        model.addAttribute("storages",storages);
    }


    @ResponseBody
    @RequestMapping(value="detail", method=RequestMethod.POST)
    public  Storage storageDetail(@RequestBody @RequestParam("mid")Long id){
        ModelAndView mv = new ModelAndView();
        if(id!=null&&!"".equals(id)){
            StorageExample storageExample = new StorageExample();
            StorageExample.Criteria criteria = storageExample.createCriteria();
            criteria.andMaterialIdEqualTo(id);
            List<Storage> storages = storageMapper.selectByExample(storageExample);

            if(storages!=null){
                mv.setViewName("university/storage");
                return storages.get(0);
            }else{
                throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
            }
        }else {
            throw new CustomizeException(CustomizeErrorCode.Object_Not_Found);
        }
    }

    @RequestMapping("/add")
    public String storageAdd(@Valid InStorageDTO storage, Model model, BindingResult result){
        if(result.hasErrors()){
            System.out.println("err");
            return "redirect:/storage/list";
        }
        if(storage!=null){
            storageService.insert(storage);

        }
        list(model);
        return "redirect:/storage/list";
    }

    @RequestMapping("/reduce")
    public String storageReduce(@Valid Storage storage, Model model, BindingResult result){
        if(result.hasErrors()){
            System.out.println("err");
            return "redirect:/storage/list";
        }
        if(storage!=null){
            storageMapper.insertSelective(storage);
        }
        list(model);
        return "redirect:/storage/list";
    }


}
