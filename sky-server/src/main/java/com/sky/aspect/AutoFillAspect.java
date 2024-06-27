package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import com.sun.tracing.Probe;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

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
        AutoFill fill = signature.getMethod().getAnnotation(AutoFill.class);
        Object[] objects = joinPoint.getArgs();
        Object object = objects[0];
        LocalDateTime now = LocalDateTime.now();
        Long id = BaseContext.getCurrentId();

        if(fill.value() == OperationType.INSERT){
            try {
                Method setCreateTime = object.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setUpdateTime = object.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setCreateUser = object.getClass().getDeclaredMethod("setCreateUser", Long.class);
                Method setUpdateUser = object.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                setCreateTime.invoke(object,now);
                setUpdateTime.invoke(object,now);
                setCreateUser.invoke(object,id);
                setUpdateUser.invoke(object,id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(fill.value() == OperationType.UPDATE){
            try {;
                Method setUpdateTime = object.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = object.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                setUpdateTime.invoke(object,now);
                setUpdateUser.invoke(object,id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
