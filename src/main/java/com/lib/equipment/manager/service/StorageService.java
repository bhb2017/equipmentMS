package com.lib.equipment.manager.service;

import com.lib.equipment.manager.dto.InStorageDTO;
import com.lib.equipment.manager.dto.UpdateStorage;
import com.lib.equipment.manager.exception.CustomizeErrorCode;
import com.lib.equipment.manager.exception.CustomizeException;
import com.lib.equipment.manager.mapper.InStorageMapper;
import com.lib.equipment.manager.mapper.OutStorageMapper;
import com.lib.equipment.manager.mapper.StorageMapper;
import com.lib.equipment.manager.model.InStorage;
import com.lib.equipment.manager.model.OutStorage;
import com.lib.equipment.manager.model.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
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
    @Autowired
    private OutStorageMapper outStorageMapper;


    public void insert(InStorageDTO inStorageDTO) {
        try {
            Storage storage = new Storage();
            BeanUtils.copyProperties(inStorageDTO,storage);
            storageMapper.insertSelective(storage);
            InStorage inStorage = new InStorage();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date date = sdf.parse(inStorageDTO.getInTime());
            inStorage.setInTime(date);
            inStorage.setMaterialId(inStorageDTO.getMaterialId());
            inStorage.setNum(inStorageDTO.getNum());

            inStorageMapper.insert(inStorage);
        }catch (Exception e){
            log.error("插入错误:{}",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

        }
    }

    public void update(UpdateStorage updateStorage) {
        try {
            Storage storage = new Storage();
            storage.setId(updateStorage.getNewid());
            storage.setPlace(updateStorage.getNewplace());
            storage.setNum(updateStorage.getNewnum());
            storage.setMaterialId(updateStorage.getMaterialid());
            Storage oldStorage = storageMapper.selectByPrimaryKey(updateStorage.getNewid());
            Integer oldStorageNum=0;
            if(oldStorage!=null&&!"".equals(oldStorage)){
              oldStorageNum = oldStorage.getNum();
            }

            OutStorage outStorage =new OutStorage();
            outStorage.setNum(oldStorageNum-updateStorage.getNewnum());
            outStorage.setMaterialId(updateStorage.getMaterialid());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(updateStorage.getNewschoolTime());
            outStorage.setOutTime(date);
            outStorage.setRemark(updateStorage.getRemark());
            if(oldStorageNum>=updateStorage.getNewnum()){
                outStorageMapper.insert(outStorage);
            }else {
                InStorage inStorage = new InStorage();
                inStorage.setInTime(date);
                inStorage.setMaterialId(updateStorage.getMaterialid());
                inStorage.setNum(updateStorage.getNewnum()-oldStorageNum);
                inStorage.setRemark(updateStorage.getRemark());
                inStorageMapper.insert(inStorage);
            }
            if("".equals(storage.getId())||storage.getId()==null){
                storageMapper.insert(storage);
            }else {

                storageMapper.updateByPrimaryKeySelective(storage);
            }

        }catch (Exception e){
            log.error("更新失败：{}",e.getMessage());
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

        }
    }
}
