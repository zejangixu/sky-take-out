package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
