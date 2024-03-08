package com.example.dao;

import com.example.domain.Account;
import org.apache.ibatis.annotations.*;


@Mapper
public interface AccountDao {
    @Insert("insert into account_info (id,password,balance,type,deposit,owner) values (#{id},#{password},#{balance},#{type},#{deposit},#{owner})")
    public int insert(Account account);

    @Select("select balance from account_info where id = #{id}")
    public double getBalanceById(String id);

    @Update("update account_info set balance = #{balance} where id = #{id}")
    public int updateBalanceById(String id, double balance);

    @Select("select type from account_info where id = #{id}")
    public String getTypeById(String id);

    @Update("update account_info set password = #{password} where id = #{id}")
    public int updatePasswordById(String id, String password);

    @Select("select password from account_info where id = #{id}")
    public String getPasswordById(String id);

    @Select("select owner from account_info where id = #{id}")
    public String getOwnerById(String id);

    @Delete("delete from account_info where id = #{id}")
    public int delete(String id);
}
