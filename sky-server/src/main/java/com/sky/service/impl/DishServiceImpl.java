package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    DishMapper dishMapper;
    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<Dish> dishPage = dishMapper.page(dishPageQueryDTO.getName(),dishPageQueryDTO.getCategoryId());
        List<Dish> list = dishPage.getResult();
        return new PageResult(dishPage.getTotal(),list);
    }

    @Override
    public void addDish(DishDTO dto) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dto,dish);

        dishMapper.insertDish(dish);
        List<DishFlavor> flavors = dto.getFlavors();
        for (DishFlavor flavor : flavors) {
            log.info("当前口味插入：{}",flavor);
            flavor.setDishId(dish.getId());
            dishMapper.insertDishFlover(flavor);
        }
    }

    @Override
    public void StopOrStart(Long id, Integer status) {
        Dish dish = Dish.builder().id(id).status(status).build();
        dishMapper.updateDish(dish);
    }
}
