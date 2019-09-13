package com.lib.equipment.manager.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.lib.equipment.manager.dto.ExcelReadMaterial;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.MaterialMapper;
import com.lib.equipment.manager.model.Material;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class DemoDataListener extends AnalysisEventListener<ExcelReadMaterial> {
    @Autowired
    private MaterialMapper materialMapper;

    private static final int BATCH_COUNT=100;
    List<ExcelReadMaterial> list = new ArrayList<ExcelReadMaterial>();


    public void invoke(ExcelReadMaterial material, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(material));
        System.out.println("data:"+material);
        list.add(material);
        if(list.size()>=BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData() {
        try {
            for (ExcelReadMaterial material : list) {
                System.out.println(material);
                Material m =new Material();
                BeanUtils.copyProperties(material,m);
                materialMapper.insertSelective(m);
            }
        }catch (Exception e){
            throw new CustomizeException(CustomizeErrorCode.Read_Excel_Error);
        }

    }
}
