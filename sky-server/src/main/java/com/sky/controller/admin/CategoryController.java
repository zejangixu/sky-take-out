package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PutMapping
    @ApiOperation("修改分类")
    public Result updateCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult =   categoryService.queryPageCategory(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> TypeList(Integer type){
        List<Category> categories = categoryService.queryCategorysByType(type);
        return Result.success(categories);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用分类")
    public Result StartOrStopStatus(@PathVariable("status") Integer status,Long id){
       categoryService.startOrStop(id,status);
        return Result.success();
    }

    @PostMapping
    @ApiOperation("新增分类")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }
    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public Result deleteCategory(Long id){
        categoryService.deleteCategoryById(id);
        return Result.success();
    }

}
