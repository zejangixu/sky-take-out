package com.sky.aspect;

import com.sky.annotation.AutoFill;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * 前置通知 ：为公共字段赋值
     */
    @Before("autoFillPointCut()")
    public void autofill(JoinPoint joinPoint){
        log.info("开始进行公共字段填充");
        //获取注解的类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

    }

}
