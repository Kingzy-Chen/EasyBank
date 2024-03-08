package com.example.service.impl;

import com.example.dao.LogDao;
import com.example.domain.Log;
import com.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    // 创建日志
    public boolean createLog(Log log) {
        // 向数据库插入新数据
        return logDao.insert(log) > 0;
    }

    @Override
    // 查询账号的所有交易日志
    public List<Log> queryTxnLogs(String id) {
        return logDao.getAllTxnById(id);
    }

    @Override
    // 按照用户名查询日志
    public List<Log> queryLogsByUsername(String username) {
        return logDao.getLogsByUsername(username);
    }

    @Override
    // 按照部门查询日志
    public List<Log> queryLogsByDepartment(String department, int i) {
        if (i == 1) {
            return logDao.getLogsByDepartment_1(department);
        }
        if (i == 2) {
            return logDao.getLogsByDepartment_2(department);
        }
        return null;
    }

    @Override
    // 查询所有雇员操作日志
    public List<Log> queryLogs() {
        return logDao.getLogs();
    }
}
