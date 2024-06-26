package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @AutoFill(OperationType.UPDATE)
    void updateCategory(Category category);

    Page<Category> selectPageCategory(String name,Integer type);

    @Select("select * from category where  type = #{type}")
    List<Category> queryCategorysByType(Integer type);

    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "VALUES (#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insertCategory(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteCategoryById(Long id);
}
