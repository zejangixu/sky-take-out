package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @GetMapping("/page")
    @ApiOperation("分类查询套餐")
    public Result<PageResult> page(SetmealPageQueryDTO dto){
        PageResult pageResult = setmealService.page(dto);
        return Result.success(pageResult);
    }
    @PostMapping
    @ApiOperation("新增套餐")
    public Result addSetmeal(@RequestBody  SetmealDTO dto){
        setmealService.addSetmael(dto);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启售禁售套餐")
    public Result StartOrStopStatus(@PathVariable  Integer status,Long id){
        log.info("起售禁售套餐id：{}，修改状态为：{}",id,status);
        setmealService.StartOrStopSetmeal(id,status);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根绝id查询套餐")
    public Result<SetmealVO> findSetmeal(@PathVariable Long id){
        SetmealVO setmealVO = setmealService.findSetmealById(id);

        return Result.success(setmealVO);
    }

    @PutMapping
    @ApiOperation("修改套餐")
    @Transactional
    public Result updateSetmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("修改后的套餐信息为：{}",setmealDTO);
        setmealService.updateSetmeal(setmealDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result deleteSetmeal(@RequestParam List<Long> ids){
        setmealService.deleteSetmeal(ids);
        return Result.success();
    }
}
