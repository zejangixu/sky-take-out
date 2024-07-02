package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealMapper setmealMapper;
    @Autowired
    DishMapper dishMapper;
    @Override
    public PageResult page( SetmealPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(),dto.getPageSize());

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(dto,setmeal);
        setmeal.setCategoryId(dto.getCategoryId());
        Page<SetmealVO> setmeals = setmealMapper.page(setmeal);
        return new PageResult(setmeals.getTotal(),setmeals.getResult());
    }

    @Override
    public void addSetmael(SetmealDTO dto) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(dto,setmeal);
        setmealMapper.insertSetmeal(setmeal);

        List<SetmealDish> setmealDishes = dto.getSetmealDishes();
        for (SetmealDish dish : setmealDishes) {
            if(dishMapper.queryDishVOById(dish.getDishId()).getStatus() == StatusConstant.DISABLE){
                throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
            }
            dish.setSetmealId(setmeal.getId());
            setmealMapper.insertSetmealDish(dish);
        }
    }

    @Override
    public void StartOrStopSetmeal(Long id, Integer status) {
        Setmeal setmeal = Setmeal.builder().id(id).status(status).build();
        setmealMapper.updateSetmeal(setmeal);
    }

    @Override
    public SetmealVO findSetmealById(Long id) {
        SetmealVO setmealVO = setmealMapper.selectSetmealVOById(id);
        List<SetmealDish> setmealDishes = setmealMapper.selectSetmealDishBySetmealId(id);
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;
    }

    @Override
    @Transactional
    public void updateSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.updateSetmeal(setmeal);

        Long id = setmeal.getId();
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();

        setmealMapper.deleteSetmealDishById(id);

        for (SetmealDish dish : setmealDishes) {
            dish.setSetmealId(id);
        }
        setmealMapper.insertSetmealDishBatch(setmealDishes);
    }

    @Override
    @Transactional
    public void deleteSetmeal(List<Long> ids) {
        for (Long id : ids) {
            SetmealVO setmealVO = setmealMapper.selectSetmealVOById(id);
            if (setmealVO.getStatus() == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }
        for (Long id : ids) {
            setmealMapper.deleteSetmealById(id);
            setmealMapper.deleteSetmealDishById(id);
        }
    }

    @Override
    public List<Setmeal> getByCategoryId(Long categoryId) {
        List<Setmeal> setmeals = setmealMapper.selectSetmealByCategoryId(categoryId);
        return setmeals;
    }

    @Override
    public List<DishItemVO> findDishItemsBySetmealId(Long id) {
        List<DishItemVO> dishItemVOS = dishMapper.queryDishItemBySetmealId(id);
        return dishItemVOS;
    }
}
