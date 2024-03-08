package com.example.service.impl;

import com.example.controller.Code;
import com.example.dao.EnterpriseUserDao;
import com.example.domain.EnterpriseUser;
import com.example.exception.BussinessException;
import com.example.service.EnterpriseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnterpriseUserServiceImpl implements EnterpriseUserService {
    @Autowired
    private EnterpriseUserDao enterpriseUserDao;

    @Override
    // 创建用户
    public boolean createUser(EnterpriseUser user) {
        // 向数据库插入新数据
        try {
            return enterpriseUserDao.insert(user) > 0;
        } catch (Exception e) {
            throw new BussinessException(Code.SAVE_ERR, "用户名已存在！请重试！", e);
        }
    }

    @Override
    // 查看用户是否为超级管理员
    public boolean checkSuperController(String username) {
        String super_controller = enterpriseUserDao.getSuperControllerByUsername(username);
        if (super_controller == null) {
            throw new BussinessException(Code.GET_ERR, "输入用户名不存在！请重试！");
        }
        return super_controller.equals("1");
    }

    @Override
    // 删除用户, 相同企业的管理员都将被删除
    public boolean deleteUsers(String enterprise) {
        if (enterpriseUserDao.deleteByEnterprise(enterprise) <= 0) {
            throw new BussinessException(Code.DELETE_ERR, "企业名称不存在！请重试！");
        }
        return true;
    }

    @Override
    // 删除用户, 只删除单个管理员
    public boolean deleteUser(String username) {
        if (enterpriseUserDao.deleteByUsername(username) <= 0) {
            throw new BussinessException(Code.DELETE_ERR, "用户名不存在！请重试！");
        }
        return true;
    }

    @Override
    // 修改密码
    public boolean changePassword(String username, String password) {
        if (enterpriseUserDao.updatePasswordByUsername(username, password) <= 0) {
            throw new BussinessException(Code.UPDATE_ERR, "用户名不存在！请重试！");
        }
        return true;
    }

    @Override
    // 查询密码
    public String queryPassword(String username) {
        String password = enterpriseUserDao.getPasswordByUsername(username);
        if (password == null) {
            throw new BussinessException(Code.GET_ERR, "输入用户名不存在！请重试！");
        }
        return password;
    }

    @Override
    // 查询企业
    public String queryEnterprise(String username) {
        String enterprise = enterpriseUserDao.getEnterpriseByUsername(username);
        if (enterprise == null) {
            throw new BussinessException(Code.GET_ERR, "输入用户名不存在！请重试！");
        }
        return enterprise;
    }

    @Override
    public String queryCtfId(String username) {
        String ctf_id = enterpriseUserDao.getCtfIdByUsername(username);
        if (ctf_id == null) {
            throw new BussinessException(Code.GET_ERR, "用户名不存在！请重试！");
        }
        return ctf_id;
    }

    @Override
    // 检查用户名是否已存在
    public boolean checkUsernameDuplicate(String username) {
        return enterpriseUserDao.getCntByUsername(username) > 0;
    }

    @Override
    // 检查企业名称是否已存在
    public boolean checkEnterpriseDuplicate(String enterprise) {
        return enterpriseUserDao.getCntByEnterprise(enterprise) > 0;
    }
}
