package com.example.hywm.service.impl;

import com.example.hywm.common.PageUtils;
import com.example.hywm.common.WMContonst;
import com.example.hywm.dto.DishDto;
import com.example.hywm.entity.Category;
import com.example.hywm.entity.Dish;
import com.example.hywm.entity.DishFlavor;
import com.example.hywm.mapper.CategoryMapper;
import com.example.hywm.mapper.DishMapper;
import com.example.hywm.service.DishService;
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
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 19:53
 **/
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public PageResult selectDishPage(PageReqVo pageReqVo) throws Exception {
        int page = pageReqVo.getPage();
        int pageSize = pageReqVo.getPageSize();
        Page<Object> pageHelp = PageHelper.startPage(page, pageSize);
        List<DishDto> dishDtoList = new ArrayList<>();
        if(StringUtils.isNotEmpty(pageReqVo.getName())){
            String name = "%" + pageReqVo.getName() + "%";
            dishDtoList = dishMapper.selectDishLikeName(name);
        }else{
            dishDtoList = dishMapper.selectAllDish();
        }
        List<DishDto> collect = dishDtoList.stream().sorted(Comparator.comparing(Dish::getUpdateTime).reversed()).collect(Collectors.toList());
        PageInfo<DishDto> pageInfo = new PageInfo<>(collect);
        PageResult pageResult = PageUtils.getPageResult(pageInfo,pageHelp);
        return pageResult;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertDish(DishDto dishDto) throws Exception {
        String dishUuid = UUID.randomUUID().toString().replaceAll("-", "");
        List<DishFlavor> flavors = dishDto.getFlavors();
        for(DishFlavor flavor : flavors){
            String flavorUuid = UUID.randomUUID().toString().replaceAll("-", "");
            flavor.setId(flavorUuid);
            flavor.setDishId(dishUuid);
            flavor.setCreateUser(dishDto.getCreateUser());
            flavor.setUpdateUser(dishDto.getUpdateUser());
            flavor.setCreateTime(LocalDateTime.now());
            flavor.setUpdateTime(LocalDateTime.now());
        }
        dishDto.setId(dishUuid);
        dishDto.setCreateTime(LocalDateTime.now());
        dishDto.setUpdateTime(LocalDateTime.now());
        Integer flavor = dishMapper.insertDishFlavor(flavors);
        Integer integer = dishMapper.insertDish(dishDto);
        if (integer != 1 && flavor != flavors.size()){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editDish(DishDto dishDto) throws Exception {
        List<DishFlavor> addList = new ArrayList<>();
        List<DishFlavor> editList = new ArrayList<>();
        dishDto.setUpdateTime(LocalDateTime.now());
        List<DishFlavor> flavors = dishDto.getFlavors();
        List<DishFlavor> dishFlavorList = dishMapper.selectDishFlavor(dishDto.getId());
        List<DishFlavor> deleteList = dishFlavorList.stream().filter(obj -> !flavors.contains(obj)).collect(Collectors.toList());
        for(DishFlavor flavor : flavors){
            flavor.setUpdateUser(dishDto.getUpdateUser());
            flavor.setUpdateTime(LocalDateTime.now());
            flavor.setDishId(dishDto.getId());
            if(StringUtils.isBlank(flavor.getId())){
                flavor.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                flavor.setCreateUser(dishDto.getUpdateUser());
                flavor.setCreateTime(LocalDateTime.now());
                addList.add(flavor);
            }else {
                editList.add(flavor);
            }
        }
        Integer editNumber = 0;
        Integer addNumber = 0;
        Integer deleteNumber = 0;
        if(!CollectionUtils.isEmpty(editList)){
            editNumber = dishMapper.editDishFlavor(editList);
        }
        if(!CollectionUtils.isEmpty(addList)){
            addNumber = dishMapper.insertDishFlavor(addList);
        }
        Integer integer = dishMapper.editDish(dishDto);
        if(!CollectionUtils.isEmpty(deleteList)){
            List<String> stringList = deleteList.stream().map(DishFlavor::getId).collect(Collectors.toList());
            deleteNumber = dishMapper.deleteDishFlavor(stringList);
        }
        if (integer != 1 || addNumber != addList.size() || editNumber != editList.size()
            || deleteNumber != deleteList.size()){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDish(String id) throws Exception {
        List<String> stringList = Arrays.asList(id.split(","));
        Integer integer = dishMapper.deleteDish(stringList);
        dishMapper.deleteByDishId(stringList);
        if (integer != stringList.size()){
            return false;
        }
        return true;
    }

    @Override
    public DishDto selectDishById(String id) throws Exception {
        DishDto dishDto = new DishDto();
        Dish dish = dishMapper.selectDishById(id);
        List<DishFlavor> dishFlavors = dishMapper.selectDishFlavor(id);
        Category category = categoryMapper.selectCategoryById(dish.getCategoryId());
        BeanUtils.copyProperties(dish,dishDto);
        dishDto.setFlavors(dishFlavors);
        dishDto.setCategoryName(category.getName());
        return dishDto;
    }

    @Override
    public Boolean editDishStatus(String status,String id) throws Exception {
        Integer integer;
        List<String> stringList = Arrays.asList(id.split(","));
        if(WMContonst.TS.equals(status)){
            integer = dishMapper.editDishStatusQS(stringList);
        }else {
            integer = dishMapper.editDishStatusTS(stringList);
        }
        if (integer != stringList.size()){
            return false;
        }
        return true;
    }

    @Override
    public List<Dish> selectDishByCategoryId(String id) throws Exception {
        List<Dish> dishList = dishMapper.selectDishByCategoryId(id);
        return dishList;
    }

}
