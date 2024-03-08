package com.example.dao;

import com.example.domain.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface LogDao {
    @Insert("insert into log_info (username,role,op,account_id,amount,timestamp) values (#{username},#{role},#{op},#{account_id},#{amount},#{timestamp})")
    public int insert(Log log);

    @Select("select * from log_info where account_id = #{id} and op in ('存款','取款','转账') limit 10")
    public List<Log> getAllTxnById(String id);

    @Select("select * from log_info where username = #{username} limit 10")
    public List<Log> getLogsByUsername(String username);

    @Select("select * from (log_info inner join staff_info on log_info.username = staff_info.username) where department = #{department} and type in ('operator')")
    public List<Log> getLogsByDepartment_1(String department);

    @Select("select * from (log_info inner join staff_info on log_info.username = staff_info.username) where department = #{department} and type in ('operator', 'manager')")
    public List<Log> getLogsByDepartment_2(String department);

    @Select("select * from (log_info inner join staff_info on log_info.username = staff_info.username) where type in ('operator', 'manager', 'boss') limit 10")
    public List<Log> getLogs();
}
