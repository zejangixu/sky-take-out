package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

public interface DishService {
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    void addDish(DishDTO dto);

    void StopOrStart(Long id, Integer status);
}
