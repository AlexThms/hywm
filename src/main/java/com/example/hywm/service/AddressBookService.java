package com.example.hywm.service;

import com.example.hywm.entity.AddressBook;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/3/5 22:09
 **/
public interface AddressBookService {

    Boolean insert(AddressBook addressBook) throws Exception;

    List<AddressBook> query(String id) throws Exception;

    AddressBook queryOne(String id) throws Exception;

    Boolean update(AddressBook addressBook) throws Exception;

    Boolean updateDefault(String id,String userId) throws Exception;

    AddressBook queryDefault(String id) throws Exception;

    Boolean delete(String id) throws Exception;
}
