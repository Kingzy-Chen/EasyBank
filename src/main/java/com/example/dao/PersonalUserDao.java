package com.example.dao;

import com.example.domain.PersonalUser;
import org.apache.ibatis.annotations.*;


@Mapper
public interface PersonalUserDao {
    @Insert("insert into personal_user_info (username,password,ctf_id,type) values (#{username},#{password},#{ctf_id},#{type})")
    public int insert(PersonalUser user);

    @Delete("delete from personal_user_info where username = #{username}")
    public int delete(String username);

    @Update("update personal_user_info set password = #{password} where username = #{username}")
    public int updatePasswordByUsername(String username, String password);

    @Select("select password from personal_user_info where username = #{username}")
    public String getPasswordByUsername(String username);

    @Select("select ctf_id from personal_user_info where username = #{username}")
    public String getCtfIdByUsername(String username);

    @Select("select type from personal_user_info where username = #{username}")
    public String getTypeByUsername(String username);

    @Select("update personal_user_info set type = #{type} where username = #{username}")
    public void updateTypeByUsername(String username, String type);
}
