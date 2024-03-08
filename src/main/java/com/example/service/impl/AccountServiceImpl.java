package com.example.service.impl;

import com.example.controller.Code;
import com.example.dao.AccountDao;
import com.example.domain.Account;
import com.example.exception.BussinessException;
import com.example.exception.SystemException;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    // 开户
    public boolean createAccount(Account account) {
        // 向数据库插入新数据
        try {
            return accountDao.insert(account) > 0;
        } catch (Exception e) {
            throw new BussinessException(Code.SAVE_ERR, "账号已存在！请重试！", e);
        }
    }

    @Override
    // 存款
    public boolean depositToAccount(String id, double amount) {
        // 查询账户的余额
        double balance;
        try {
            balance = accountDao.getBalanceById(id);
        } catch (Exception e) {
            throw new BussinessException(Code.GET_ERR, "存款账号不存在！请重试！", e);
        }
        // 更新账户的余额
        try {
            return accountDao.updateBalanceById(id,balance + amount) > 0;
        } catch (Exception e) {
            throw new SystemException(Code.UPDATE_ERR, "账户余额更新异常！", e);
        }
    }

    @Override
    // 取款
    public boolean withdrawFromAccount(String id, double amount) {
        // 查询账户的余额
        double balance;
        try {
            balance = accountDao.getBalanceById(id);
        } catch (Exception e) {
            throw new BussinessException(Code.GET_ERR, "取款账号不存在！请重试！", e);
        }
        // 检查余额是否能够扣款
        if (balance - amount < 0) {
            throw new BussinessException(Code.BUSINESS_ERR, "取款账户余额不足！请重试！");
        }

        // ==== 企业账户余额不能小于1万元 ====
        String type = accountDao.getTypeById(id);
        if (type.equals("enterprise") && (balance - amount) < 10000) {
            throw new BussinessException(Code.BUSINESS_ERR, "取款后企业用户的账户余额不能小于1万元！");
        }

        // 更新账户的余额
        try {
            return accountDao.updateBalanceById(id,balance - amount) > 0;
        } catch (Exception e) {
            throw new SystemException(Code.UPDATE_ERR, "账户余额更新异常！", e);
        }
    }

    @Override
    // 转账
    public boolean transfer(String from_id, String to_id, double amount) {
        // 查询账户的余额
        double from_balance;
        double to_balance;
        try {
            from_balance = accountDao.getBalanceById(from_id);
        } catch (Exception e) {
            throw new BussinessException(Code.GET_ERR, "转出账号不存在！请重试！", e);
        }
        try {
            to_balance = accountDao.getBalanceById(to_id);
        } catch (Exception e) {
            throw new BussinessException(Code.GET_ERR, "转入账号不存在！请重试！", e);
        }

        // ==== 转账只能在个人用户之间、企业用户之间进行 ====
        String from_type = accountDao.getTypeById(from_id);
        String to_type = accountDao.getTypeById(to_id);
        if (!from_type.equals(to_type)) {
            throw new BussinessException(Code.BUSINESS_ERR, "转账只能在企业用户与企业用户间、个人用户与个人用户间进行");
        }
        // ==== 企业账户余额不能小于1万元 ====
        if (from_type.equals("enterprise") && (from_balance - amount) < 10000) {
            throw new BussinessException(Code.BUSINESS_ERR, "转账后企业用户的转出账户余额不能小于1万元！");
        }
        // 检查转出账户余额是否能够扣款
        if (from_balance - amount < 0) {
            throw new BussinessException(Code.BUSINESS_ERR, "转出账户余额不足！请重试！");
        }

        // 更新账户的余额
        try {
            int flg1 = accountDao.updateBalanceById(from_id, from_balance - amount);
            int flg2 = accountDao.updateBalanceById(to_id, to_balance + amount);
            return (flg1 > 0) && (flg2 > 0);
        } catch (Exception e) {
            throw new SystemException(Code.UPDATE_ERR, "账户余额更新异常！", e);
        }
    }

    @Override
    // 查询余额
    public double queryBalance(String id) {
        try {
            return accountDao.getBalanceById(id);
        } catch (Exception e){
            throw new BussinessException(Code.GET_ERR, "查询账号不存在！请重试！", e);
        }
    }

    @Override
    // 修改密码
    public boolean changePassword(String id, String password) {
        if (accountDao.updatePasswordById(id, password) <= 0) {
            throw new BussinessException(Code.UPDATE_ERR, "账户不存在！请重试！");
        }
        return true;
    }

    @Override
    // 查询密码
    public String queryPassword(String id) {
        String password = accountDao.getPasswordById(id);
        if (password == null) {
            throw new BussinessException(Code.GET_ERR, "输入账号不存在！请重试！");
        }
        return password;
    }

    @Override
    // 查询持有者
    public String queryOwner(String id) {
        String owner = accountDao.getOwnerById(id);
        if (owner == null) {
            throw new BussinessException(Code.GET_ERR, "输入账号不存在！请重试！");
        }
        return owner;
    }

    @Override
    // 查询账户持有者为个人用户/企业用户
    public String queryType(String id) {
        String type = accountDao.getTypeById(id);
        if (type == null) {
            throw new BussinessException(Code.GET_ERR, "输入账号不存在！请重试！");
        }
        return type;
    }

    @Override
    // 销户
    public boolean deleteAccount(String id) {
        if (accountDao.delete(id) <= 0) {
            throw new BussinessException(Code.DELETE_ERR, "账号不存在！请重试！");
        }
        return true;
    }
}
