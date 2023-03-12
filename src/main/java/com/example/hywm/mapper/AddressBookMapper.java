package com.example.hywm.mapper;

import com.example.hywm.entity.AddressBook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/3/5 22:09
 **/
@Repository
public interface AddressBookMapper {

    Integer insertAddress(AddressBook addressBook);

    List<AddressBook> selectAddress(String id);

    AddressBook selectAddressOne(String id);

    AddressBook selectDefault(String id);

    Integer updateAddress(AddressBook addressBook);

    Integer updateAddressDefault(Integer isDefault,String id);

    Integer deleteAddress(String id);
}
