package com.example.controller;

import com.example.domain.Log;
import com.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/createLog")
    public Result createLog(@RequestBody Log log) {
        boolean flg = logService.createLog(log);
        return new Result(flg, flg ? Code.SAVE_OK : Code.SAVE_ERR, flg ? "日志写入成功！" : "日志写入失败！");
    }

    @PostMapping("/queryTxnLogs/account_id={account_id}")
    public Result queryTxnLogs(@PathVariable String account_id) {
        List<Log> logList = logService.queryTxnLogs(account_id);
        Integer code = (logList != null ? Code.GET_OK : Code.GET_ERR);
        String msg = (logList != null ? "" : "查询的账户号不存在！");
        return new Result(logList, code, msg);
    }

    @PostMapping("/queryLogs/username={username}")
    public Result queryLogsByUsername(@PathVariable String username) {
        List<Log> logList = logService.queryLogsByUsername(username);
        Integer code = (logList != null ? Code.GET_OK : Code.GET_ERR);
        String msg = (logList != null ? "" : "查询的用户号不存在！");
        return new Result(logList, code, msg);
    }

    @PostMapping("/queryLogs/department={department}&i={i}")
    public Result queryLogsByEnterprise(@PathVariable String department, @PathVariable int i) {
        List<Log> logList = logService.queryLogsByDepartment(department, i);
        Integer code = (logList != null ? Code.GET_OK : Code.GET_ERR);
        String msg = (logList != null ? "" : "");
        return new Result(logList, code, msg);
    }

    @PostMapping("/queryLogs")
    public Result queryLogs() {
        List<Log> logList = logService.queryLogs();
        Integer code = (logList != null ? Code.GET_OK : Code.GET_ERR);
        String msg = (logList != null ? "" : "");
        return new Result(logList, code, msg);
    }
}
