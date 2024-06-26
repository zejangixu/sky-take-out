package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
     List<Category> queryCategorysByType(Integer type);


    void updateCategory(CategoryDTO categoryDTO);

    PageResult queryPageCategory(CategoryPageQueryDTO categoryPageQueryDTO);

    void startOrStop(Long id, Integer status);

    void addCategory(CategoryDTO categoryDTO);

    void deleteCategoryById(Long id);
}
