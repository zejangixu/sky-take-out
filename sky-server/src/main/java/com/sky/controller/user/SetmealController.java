package com.sky.controller.user;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @GetMapping("/list")
    @ApiOperation("根据分类id查询套餐")
    public Result<List<Setmeal>> getByCategoryId(Long categoryId){
        List<Setmeal> setmeals = setmealService.getByCategoryId(categoryId);
        return Result.success(setmeals);
    }

    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> getDishBySetmealId(@PathVariable Long id){
        List<DishItemVO> dishItemVOS = setmealService.findDishItemsBySetmealId(id);
        return Result.success(dishItemVOS);
    }
}
