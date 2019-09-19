package com.lib.equipment.manager.service;

import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.MaterialMapper;
import com.lib.equipment.manager.model.Material;
import com.lib.equipment.manager.model.MaterialExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
