package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("ShopControllerUser")
@RequestMapping("/user/shop")
@Slf4j
public class ShopController {
    public final String SHOP_STATUS = "SHOP_STATUS";
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> ShopStatus(){
        Integer o = (Integer) redisTemplate.opsForValue().get(SHOP_STATUS);
        return Result.success(o);
    }
}
