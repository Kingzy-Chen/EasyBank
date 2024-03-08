package com.example.dao;

import com.example.domain.Staff;
import org.apache.ibatis.annotations.*;


@Mapper
public interface StaffDao {
    @Insert("insert into staff_info (username,password,type,department) values (#{username},#{password},#{type},#{department})")
    public int insert(Staff staff);

    @Delete("delete from staff_info where username = #{username}")
    public int delete(String username);

    @Update("update staff_info set password = #{password} where username = #{username}")
    public int updatePasswordByUsername(String username, String password);

    @Select("select password from staff_info where username = #{username}")
    public String getPasswordByUsername(String username);

    @Select("select type from staff_info where username = #{username}")
    public String getTypeByUsername(String username);

    @Select("select department from staff_info where username = #{username}")
    public String getDepartmentByUsername(String username);
}
