package com.example.hywm.mapper;

import com.example.hywm.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    @Select("select * from employee where username = #{username}")
    Employee selectEmployByName(String username);

    @Insert("insert into employee (id,username,NAME,PASSWORD,phone,sex,id_number,create_time,update_time,create_user,update_user) " +
            "VALUES (#{id},#{username},#{name},#{password},#{phone},#{sex},#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    Integer insertEmploy(Employee employee);

    @Select("select * from employee")
    List<Employee> selectAllEmployee();

    @Select("select * from employee where name like #{name}")
    List<Employee> selectEmployeeLikeName(String name);

    @Select("select id,username,NAME,phone,sex,id_number as idNumber from employee where id = #{id}")
    Employee selectEmployById(String id);


    Integer editEmployeeById(Employee employee);
}
