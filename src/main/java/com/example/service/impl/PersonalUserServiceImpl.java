package com.example.service.impl;

import com.example.controller.Code;
import com.example.dao.PersonalUserDao;
import com.example.domain.PersonalUser;
import com.example.exception.BussinessException;
import com.example.service.PersonalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonalUserServiceImpl implements PersonalUserService {
    @Autowired
    private PersonalUserDao personalUserDao;

    @Override
    // 创建用户
    public boolean createUser(PersonalUser user) {
        // 向数据库插入新数据
        try {
            return personalUserDao.insert(user) > 0;
        } catch (Exception e) {
            throw new BussinessException(Code.SAVE_ERR, "用户名已存在！请重试！", e);
        }
    }

    @Override
    // 删除用户
    public boolean deleteUser(String username) {
        if (personalUserDao.delete(username) <= 0) {
            throw new BussinessException(Code.DELETE_ERR, "用户名不存在！请重试！");
        }
        return true;
    }

    @Override
    // 修改密码
    public boolean changePassword(String username, String password) {
        if (personalUserDao.updatePasswordByUsername(username, password) <= 0) {
            throw new BussinessException(Code.UPDATE_ERR, "用户名不存在！请重试！");
        }
        return true;
    }

    @Override
    // 查询密码
    public String queryPassword(String username) {
        String password = personalUserDao.getPasswordByUsername(username);
        if (password == null) {
            throw new BussinessException(Code.GET_ERR, "输入用户名不存在！请重试！");
        }
        return password;
    }

    @Override
    // 查询身份证号
    public String queryCtfId(String username) {
        String ctf_id = personalUserDao.getCtfIdByUsername(username);
        if (ctf_id == null) {
            throw new BussinessException(Code.GET_ERR, "用户名不存在！请重试！");
        }
        return ctf_id;
    }

    @Override
    // 查询个人用户为普通用户/VIP用户
    public String queryType(String username) {
        String type = personalUserDao.getTypeByUsername(username);
        if (type == null) {
            throw new BussinessException(Code.GET_ERR, "用户名不存在！请重试！");
        }
        return type;
    }

    @Override
    // 普通用户升级为VIP用户
    public boolean upgrade(String username) {
        if (personalUserDao.getTypeByUsername(username).equals("VIP")) {
            throw new BussinessException(Code.UPDATE_ERR, "");
        }
        personalUserDao.updateTypeByUsername(username, "VIP");
        return true;
    }

    @Override
    // VIP用户降级为普通用户
    public boolean degrade(String username) {
        if (personalUserDao.getTypeByUsername(username).equals("common")) {
            throw new BussinessException(Code.UPDATE_ERR, "");
        }
        personalUserDao.updateTypeByUsername(username, "common");
        return true;
    }
}
