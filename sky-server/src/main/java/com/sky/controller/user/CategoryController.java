package com.sky.controller.user;

import com.sky.entity.Category;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    public Result<List<Category>> getlist(Integer type){
        String stype = type == 1?"菜品分类":"套餐分类";
        log.info("查询分类类型：{}",stype);
        List<Category> categories = categoryService.queryCategorysByType(type);
        return Result.success(categories);
    }
}
