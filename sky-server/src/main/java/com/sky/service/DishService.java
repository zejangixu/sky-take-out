package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    void addDish(DishDTO dto);

    void StopOrStart(Long id, Integer status);

    List<Dish> queryDishByCategoryId(Long categoryId);

    DishVO findDishById(Long id);

    void updateDish(DishDTO dto);
}
