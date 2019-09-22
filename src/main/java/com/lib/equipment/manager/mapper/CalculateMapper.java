package com.lib.equipment.manager.mapper;

import com.lib.equipment.manager.dto.ForecastTempDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CalculateMapper {
    List<ForecastTempDTO>selectByDate(@Param("date") String date);
}
