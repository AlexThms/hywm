package com.example.hywm.service.impl;

import com.example.hywm.common.PageUtils;
import com.example.hywm.common.WMContonst;
import com.example.hywm.dto.DishDto;
import com.example.hywm.dto.SetmealDto;
import com.example.hywm.entity.Category;
import com.example.hywm.entity.Dish;
import com.example.hywm.entity.DishFlavor;
import com.example.hywm.entity.Setmeal;
import com.example.hywm.entity.SetmealDish;
import com.example.hywm.mapper.CategoryMapper;
import com.example.hywm.mapper.DishMapper;
import com.example.hywm.mapper.SetmealMapper;
import com.example.hywm.service.SetmealService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/12 16:14
 **/
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    SetmealMapper setmealMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    DishMapper dishMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertSetmeal(SetmealDto setmealDto) throws Exception {
        String setmealUuid = UUID.randomUUID().toString().replaceAll("-", "");
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for(SetmealDish setmealDish : setmealDishes){
            String SetmealDishUuid = UUID.randomUUID().toString().replaceAll("-", "");
            setmealDish.setId(SetmealDishUuid);
            setmealDish.setSetmealId(setmealUuid);
            setmealDish.setCreateUser(setmealDto.getCreateUser());
            setmealDish.setUpdateUser(setmealDto.getUpdateUser());
            setmealDish.setCreateTime(LocalDateTime.now());
            setmealDish.setUpdateTime(LocalDateTime.now());
        }
        setmealDto.setId(setmealUuid);
        setmealDto.setCreateTime(LocalDateTime.now());
        setmealDto.setUpdateTime(LocalDateTime.now());
        Integer setmealDishNumber = setmealMapper.insetSetmealDish(setmealDishes);
        Integer setmealNumber = setmealMapper.insetSetmeal(setmealDto);
        if(setmealNumber != 1 || setmealDishNumber != setmealDishes.size()){
            return false;
        }
        return true;
    }

    @Override
    public PageResult selectSetmealPage(PageReqVo pageReqVo) throws Exception {
        int page = pageReqVo.getPage();
        int pageSize = pageReqVo.getPageSize();
        Page<Object> pageHelp = PageHelper.startPage(page, pageSize);
        List<SetmealDto> setmealList = new ArrayList<>();
        if(StringUtils.isNotEmpty(pageReqVo.getName())){
            String name = "%" + pageReqVo.getName() + "%";
            setmealList = setmealMapper.SelectSetmealLikeName(name);
        }else{
            setmealList = setmealMapper.selectAllSetmeal();
        }
        PageInfo<SetmealDto> pageInfo = new PageInfo<>(setmealList);
        PageResult pageResult = PageUtils.getPageResult(pageInfo, pageHelp);
        return pageResult;
    }

    @Override
    public SetmealDto selectSetmealById(String id) throws Exception {
        SetmealDto setmealDto = new SetmealDto();
        SetmealDto setmeal = setmealMapper.selectSetmealById(id);
        List<SetmealDish> setmealDishList = setmealMapper.selectSetmealDishById(id);
        Category category = categoryMapper.selectCategoryById(setmeal.getCategoryId());
        BeanUtils.copyProperties(setmeal,setmealDto);
        setmealDto.setSetmealDishes(setmealDishList);
        setmealDto.setCategoryName(category.getName());
        return setmealDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editSetmeal(SetmealDto setmealDto) throws Exception {
        List<SetmealDish> addList = new ArrayList<>();
        List<SetmealDish> editList = new ArrayList<>();
        setmealDto.setUpdateTime(LocalDateTime.now());
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        List<SetmealDish> setmealDishList = setmealMapper.selectSetmealDishById(setmealDto.getId());
        List<SetmealDish> deleteList = setmealDishList.stream().filter(obj -> !setmealDishes.contains(obj)).collect(Collectors.toList());
        for(SetmealDish setmealDish : setmealDishes){
            setmealDish.setUpdateUser(setmealDto.getUpdateUser());
            setmealDish.setUpdateTime(LocalDateTime.now());
            setmealDish.setSetmealId(setmealDto.getId());
            if(StringUtils.isBlank(setmealDish.getId())){
                setmealDish.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                setmealDish.setCreateUser(setmealDto.getUpdateUser());
                setmealDish.setCreateTime(LocalDateTime.now());
                addList.add(setmealDish);
            }else {
                editList.add(setmealDish);
            }
        }
        Integer editNumber = 0;
        Integer addNumber = 0;
        Integer deleteNumber = 0;
        if(!CollectionUtils.isEmpty(editList)){
            editNumber = setmealMapper.editSetmealDish(editList);
        }
        if(!CollectionUtils.isEmpty(addList)){
            addNumber = setmealMapper.insetSetmealDish(addList);
        }
        Integer integer = setmealMapper.editSetmeal(setmealDto);
        if(!CollectionUtils.isEmpty(deleteList)){
            List<String> stringList = deleteList.stream().map(SetmealDish::getId).collect(Collectors.toList());
            deleteNumber = setmealMapper.deleteSetmealDish(stringList);
        }
        if (integer != 1 || addNumber != addList.size() || editNumber != editList.size()
                || deleteNumber != deleteList.size()){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteSetmeal(String id) throws Exception {
        List<String> stringList = Arrays.asList(id.split(","));
        Integer integer = setmealMapper.deleteSetmeal(stringList);
        setmealMapper.deleteBySetmealId(stringList);
        if (integer != stringList.size()){
            return false;
        }
        return true;
    }

    @Override
    public Boolean editSetmealStatus(String status, String id) throws Exception {
        Integer integer;
        List<String> stringList = Arrays.asList(id.split(","));
        if(WMContonst.TS.equals(status)){
            integer = setmealMapper.editDishStatusQS(stringList);
        }else {
            integer = setmealMapper.editDishStatusTS(stringList);
        }
        if (integer != stringList.size()){
            return false;
        }
        return true;
    }

    @Override
    public List<Setmeal> selectSetmealByCategoryId(String id,String status) throws Exception {
        List<Setmeal> setmealList = setmealMapper.selectSetmealByCategoryId(id);
        return setmealList;
    }

    @Override
    public List<DishDto> selectDishById(String id) throws Exception {
        List<SetmealDish> setmealDishList = setmealMapper.selectSetmealDishById(id);
        List<DishDto> dishDtos = setmealDishList.stream().map((setmealDish) -> {
            DishDto dishDto = new DishDto();
            dishDto.setCopies(setmealDish.getCopies());
            //这里是为了把套餐中的菜品的基本信息填充到dto中，比如菜品描述，菜品图片等菜品的基本信息
            String dishId = setmealDish.getDishId();
            Dish dish = dishMapper.selectDishById(dishId);
            //将菜品信息拷贝到dishDto中
            BeanUtils.copyProperties(dish, dishDto);
            return dishDto;
        }).collect(Collectors.toList());
        return dishDtos;
    }
}
