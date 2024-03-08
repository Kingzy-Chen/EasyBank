package com.example.service.impl;

import com.example.controller.Code;
import com.example.dao.StaffDao;
import com.example.domain.Staff;
import com.example.exception.BussinessException;
import com.example.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    @Override
    // 创建雇员
    public boolean createStaff(Staff staff) {
        // 向数据库插入新数据
        try {
            return staffDao.insert(staff) > 0;
        } catch (Exception e) {
            throw new BussinessException(Code.SAVE_ERR, "用户名已存在！请重试！", e);
        }
    }

    @Override
    // 删除雇员
    public boolean deleteStaff(String username) {
        if (staffDao.delete(username) <= 0) {
            throw new BussinessException(Code.DELETE_ERR, "用户名不存在！请重试！");
        }
        return true;
    }

    @Override
    // 修改密码
    public boolean changePassword(String username, String password) {
        if (staffDao.updatePasswordByUsername(username, password) <= 0) {
            throw new BussinessException(Code.UPDATE_ERR, "用户名不存在！请重试！");
        }
        return true;
    }

    @Override
    // 查询密码
    public String queryPassword(String username) {
        String password = staffDao.getPasswordByUsername(username);
        if (password == null) {
            throw new BussinessException(Code.GET_ERR, "输入用户名不存在！请重试！");
        }
        return password;
    }

    @Override
    // 查询雇员类型
    public String queryType(String username) {
        String type = staffDao.getTypeByUsername(username);
        if (type == null) {
            throw new BussinessException(Code.GET_ERR, "输入用户名不存在！请重试！");
        }
        return type;
    }

    @Override
    // 查询雇员部门
    public String queryDepartment(String username) {
        String department = staffDao.getDepartmentByUsername(username);
        if (department == null) {
            throw new BussinessException(Code.GET_ERR, "输入用户名不存在！请重试！");
        }
        return department;
    }
}
