package com.example.service;

import com.example.domain.Log;
import java.util.List;


public interface LogService {

    public boolean createLog(Log log);
    public List<Log> queryTxnLogs(String id);
    public List<Log> queryLogsByUsername(String username);
    public List<Log> queryLogsByDepartment(String department, int i);
    public List<Log> queryLogs();
}
