package com.example.hywm.controller;

import com.example.hywm.common.Result;
import com.example.hywm.common.WMContonst;
import com.example.hywm.entity.AddressBook;
import com.example.hywm.service.AddressBookService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/3/5 22:04
 **/
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    @PostMapping
    public Result insert(HttpServletRequest req,@RequestBody AddressBook addressBook){
        try {
            String id = (String) req.getSession().getAttribute("User");
            addressBook.setCreateUser(id);
            addressBook.setUpdateUser(id);
            addressBook.setUserId(id);
            Boolean bool = addressBookService.insert(addressBook);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_INSERT.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        }
    }

    @GetMapping("/list")
    public Result query(HttpServletRequest req){
        try {
            String id = (String) req.getSession().getAttribute("User");
            List<AddressBook> result = addressBookService.query(id);
            return Result.success(result);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @GetMapping("/{id}")
    public Result queryOne(@PathVariable String id){
        try {
            AddressBook addressBook = addressBookService.queryOne(id);
            return Result.success(addressBook);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @PutMapping
    public Result update(@RequestBody AddressBook addressBook){
        try {
            Boolean bool = addressBookService.update(addressBook);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }

    @PutMapping("/default")
    public Result setDefaultAddress(@RequestBody AddressBook addressBook,HttpServletRequest req){
        try {
            String userId = (String) req.getSession().getAttribute("User");
            Boolean bool = addressBookService.updateDefault(addressBook.getId(),userId);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }catch (Exception e){
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }

    @GetMapping("/default")
    public Result getDefault(HttpServletRequest req){
        try {
            String id = (String) req.getSession().getAttribute("User");
            AddressBook addressBook = addressBookService.queryDefault(id);
            return Result.success(addressBook);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @DeleteMapping
    public Result delete(@RequestParam("ids") String id){
        try {
            Boolean bool = addressBookService.delete(id);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_DELETE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        }
    }
}
