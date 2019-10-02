package com.lib.equipment.manager.service;

import com.lib.equipment.manager.dto.InStorageDTO;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.InStorageMapper;
import com.lib.equipment.manager.mapper.StorageMapper;
import com.lib.equipment.manager.model.InStorage;
import com.lib.equipment.manager.model.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
@Slf4j
public class StorageService {
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private InStorageMapper inStorageMapper;

    public void insert(InStorageDTO inStorageDTO) {
        try {
            Storage storage = new Storage();
            BeanUtils.copyProperties(inStorageDTO,storage);
            storageMapper.insertSelective(storage);
            InStorage inStorage = new InStorage();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date date = sdf.parse(inStorageDTO.getInTime());
            inStorage.setInTime(date);
            inStorage.setMaterialId(inStorageDTO.getMaterial().getId());
            inStorage.setNum(inStorageDTO.getNum());

            inStorageMapper.insert(inStorage);
        }catch (Exception e){
            log.error("插入错误:{}",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

        }
    }
}
