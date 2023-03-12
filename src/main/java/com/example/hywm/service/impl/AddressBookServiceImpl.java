package com.example.hywm.service.impl;

import com.example.hywm.common.PageUtils;
import com.example.hywm.entity.AddressBook;
import com.example.hywm.mapper.AddressBookMapper;
import com.example.hywm.service.AddressBookService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/3/5 22:10
 **/
@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    AddressBookMapper addressBookMapper;

    @Override
    public Boolean insert(AddressBook addressBook) throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        addressBook.setId(uuid);
        addressBook.setCreateTime(LocalDateTime.now());
        addressBook.setUpdateTime(LocalDateTime.now());
        Integer integer = addressBookMapper.insertAddress(addressBook);
        if(integer != 1){
            return false;
        }
        return true;
    }

    @Override
    public List<AddressBook> query(String id) throws Exception {
        List<AddressBook> addressBooks = addressBookMapper.selectAddress(id);
        addressBooks.sort(Comparator.comparing(AddressBook::getIsDefault).reversed());
        return addressBooks;
    }

    @Override
    public AddressBook queryOne(String id) throws Exception {
        AddressBook addressBook = addressBookMapper.selectAddressOne(id);
        return addressBook;
    }

    @Override
    public Boolean update(AddressBook addressBook) throws Exception {
        addressBook.setUpdateTime(LocalDateTime.now());
        addressBookMapper.updateAddress(addressBook);
        return null;
    }

    @Override
    public Boolean updateDefault(String id,String userId) throws Exception {
        List<AddressBook> addressBooks = addressBookMapper.selectAddress(userId);
        List<String> collect = addressBooks.stream().map(AddressBook::getId).collect(Collectors.toList());
        List<String> strings = collect.stream().filter(o -> !o.equals(id)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(strings)){
            strings.forEach(obj->{
                addressBookMapper.updateAddressDefault(0, obj);
            });
        }
        addressBookMapper.updateAddressDefault(1,id);
        return true;
    }

    @Override
    public AddressBook queryDefault(String id) throws Exception {
        AddressBook addressBook = addressBookMapper.selectDefault(id);
        return addressBook;
    }

    @Override
    public Boolean delete(String id) throws Exception {
        Integer integer = addressBookMapper.deleteAddress(id);
        if(integer !=1 ){
            return false;
        }
        return true;
    }
}
