package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface DishMapper {
    @Select("select count(id) from dish where category_id = #{categoryId}")
    int countByCategoryId(Long categoryId);

    Page<Dish> page(String name, Integer categoryId);

    @Insert("insert into dish(name, category_id, price, image, description, status, create_time, update_time, create_user, update_user) VALUES " +
            "(#{name},#{categoryId},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @AutoFill(OperationType.INSERT)
    int insertDish(Dish dish);

    @Insert("INSERT INTO dish_flavor(DISH_ID, NAME, VALUE)" +
            "VALUES (#{dishId},#{name},#{value})")
    void insertDishFlover(DishFlavor flavor);

    void updateDish(Dish dish);
}
