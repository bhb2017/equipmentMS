package com.lib.equipment.manager.service;

import com.lib.equipment.manager.excelDate.MaterialExcel;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.MaterialMapper;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.MaterialExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MaterialSevice {
    @Autowired
    private MaterialMapper materialMapper;

    public List<Material> selectAllMaterial() {
        try {
            MaterialExample materialExample= new MaterialExample();
            materialExample.setOrderByClause("id desc");
            List<Material> materials = materialMapper.selectByExample(materialExample);
            return materials;
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

        }

    }

    public Material selectByNameAndSpecification(Material material) {
        MaterialExample materialExample =new MaterialExample();
        MaterialExample.Criteria criteria = materialExample.createCriteria();
        criteria.andNameEqualTo(material.getName());
        criteria.andSpecificationEqualTo(material.getSpecification());
        List<Material> materials = materialMapper.selectByExample(materialExample);
        if(materials!=null&&materials.size()>0){
            return materials.get(0);
        }
        return null;
    }

    public void insertMaterial(Material material) {
        try {

            materialMapper.insertSelective(material);
        }catch (Exception e){
            log.error("器材插入失败：{}",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

        }
    }

    public Material selectById(Long materialId) {

        return materialMapper.selectByPrimaryKey(materialId);
    }
}
