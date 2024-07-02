package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface DishMapper {
    @Select("select count(id) from dish where category_id = #{categoryId}")
    int countByCategoryId(Long categoryId);

    Page<DishVO> page(Dish dish);

    @Insert("insert into dish(name, category_id, price, image, description, status, create_time, update_time, create_user, update_user) VALUES " +
            "(#{name},#{categoryId},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @AutoFill(OperationType.INSERT)
    int insertDish(Dish dish);

    @Insert("INSERT INTO dish_flavor(DISH_ID, NAME, VALUE)" +
            "VALUES (#{dishId},#{name},#{value})")
    void insertDishFlover(DishFlavor flavor);

    void updateDish(Dish dish);

    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> queryDishByCategoryId(Long categoryId);

    @Select("select d.*,c.name as category_name from dish d left join category c on d.category_id = c.id where d.id = #{id}")
    DishVO queryDishVOById(Long id);

    @Select("select * from dish_flavor where dish_id = #{id}")
    List<DishFlavor> queryFlavorByDishId(Long id);

    void insertListDishFlover(List<DishFlavor> flavors);

    void deleteDishFlavorByDishId(Long id);

    List<DishVO> queryDishVOByCategoryId(Long categoryId);


    List<DishItemVO> queryDishItemBySetmealId(Long id);
}
