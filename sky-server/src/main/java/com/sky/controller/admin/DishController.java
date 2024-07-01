package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    DishService dishService;

    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }
    @PostMapping
    @ApiOperation("新增菜品")
    public Result addDish(@RequestBody DishDTO dto){
        dishService.addDish(dto);
        return Result.success();
    }
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用菜品")
    public Result StopOrStart(Long id,@PathVariable("status") Integer status){
        log.info("启用禁用菜品id{}和状态{}",id,status);
        dishService.StopOrStart(id,status);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> categoryToDish(Long categoryId){
        List<Dish> dishes = dishService.queryDishByCategoryId(categoryId);
        return Result.success(dishes);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> findDishById(@PathVariable("id") Long id){
        DishVO dvo = dishService.findDishById(id);
        return Result.success(dvo);
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result updateDish(@RequestBody DishDTO dto){
        dishService.updateDish(dto);
        return Result.success();
    }
}
