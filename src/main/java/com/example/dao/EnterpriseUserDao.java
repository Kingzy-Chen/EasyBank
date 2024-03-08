package com.example.dao;

import com.example.domain.EnterpriseUser;
import org.apache.ibatis.annotations.*;


@Mapper
public interface EnterpriseUserDao {
    @Insert("insert into enterprise_user_info (username,password,ctf_id,enterprise,super_controller) values (#{username},#{password},#{ctf_id},#{enterprise},#{super_controller})")
    public int insert(EnterpriseUser user);

    @Select("select super_controller from enterprise_user_info where username = #{username}")
    public String getSuperControllerByUsername(String username);

    @Delete("delete from enterprise_user_info where enterprise = #{enterprise}")
    public int deleteByEnterprise(String enterprise);

    @Delete("delete from enterprise_user_info where username = #{username}")
    public int deleteByUsername(String username);

    @Update("update enterprise_user_info set password = #{password} where username = #{username}")
    public int updatePasswordByUsername(String username, String password);

    @Select("select password from enterprise_user_info where username = #{username}")
    public String getPasswordByUsername(String username);

    @Select("select enterprise from enterprise_user_info where username = #{username}")
    public String getEnterpriseByUsername(String username);

    @Select("select ctf_id from enterprise_user_info where username = #{username}")
    public String getCtfIdByUsername(String username);

    @Select("select count(*) from enterprise_user_info where username = #{username}")
    public int getCntByUsername(String username);

    @Select("select count(*) from enterprise_user_info where enterprise = #{enterprise}")
    public int getCntByEnterprise(String enterprise);
}
