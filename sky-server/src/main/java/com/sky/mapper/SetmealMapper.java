package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    Page<SetmealVO> page(Setmeal setmeal);

    @Insert("insert into setmeal (category_id, name, price, status, description, image, create_time, update_time, create_user, update_user) " +
            "values (#{categoryId},#{name},#{price},#{status},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser});")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @AutoFill(OperationType.INSERT)
    void insertSetmeal(Setmeal setmeal);

    @AutoFill(OperationType.INSERT)
    void insertSetmealDish(SetmealDish setmealDish);

    @AutoFill(OperationType.UPDATE)
    void updateSetmeal(Setmeal setmeal);

    SetmealVO selectSetmealVOById(Long id);

    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteSetmealDishById(Long id);

    void insertSetmealDishBatch(List<SetmealDish> setmealDishes);

    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> selectSetmealDishBySetmealId(Long id);

    @Delete("delete from setmeal where id = #{id}")
    void deleteSetmealById(Long id);
}
