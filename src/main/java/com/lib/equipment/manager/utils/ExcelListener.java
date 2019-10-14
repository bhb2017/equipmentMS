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
public class ExcelListener extends AnalysisEventListener {
    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();
    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(o);//根据自己业务做处理
    }

    private void doSomething(Object object) {
        //1、入库调用接口
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // datas.clear();//解析结束销毁不用的资源
    }
}

