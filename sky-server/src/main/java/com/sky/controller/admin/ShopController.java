package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {
    public final String SHOP_STATUS = "SHOP_STATUS";
    @Autowired
    RedisTemplate redisTemplate;
    @PutMapping("/{status}")
    public Result setShopStatus(@PathVariable("status") Integer status){
        String s = status == 1?"营业中":"未营业";
        log.info("将营业状态设置为："+s);
        redisTemplate.opsForValue().set(SHOP_STATUS,status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> ShopStatus(){
        Integer o = (Integer) redisTemplate.opsForValue().get(SHOP_STATUS);
        return Result.success(o);
    }
}
