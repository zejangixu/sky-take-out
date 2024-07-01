package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    PageResult page(SetmealPageQueryDTO dto);

    void addSetmael(SetmealDTO dto);

    void StartOrStopSetmeal(Long id, Integer status);

    SetmealVO findSetmealById(Long id);

    void updateSetmeal(SetmealDTO setmealDTO);

    void deleteSetmeal(List<Long> ids);
}
