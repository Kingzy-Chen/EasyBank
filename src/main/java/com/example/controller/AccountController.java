package com.example.controller;

import com.example.domain.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/createAccount")
    public Result createAccount(@RequestBody Account account) {
        boolean flg = accountService.createAccount(account);
        return new Result(flg, flg ? Code.SAVE_OK : Code.SAVE_ERR);
    }

    @PostMapping("/depositToAccount/id={id}&amount={amount}")
    public Result depositToAccount(@PathVariable String id, @PathVariable double amount) {
        boolean flg = accountService.depositToAccount(id, amount);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR);
    }

    @PostMapping("/withdrawFromAccount/id={id}&amount={amount}")
    public Result withdrawFromAccount(@PathVariable String id, @PathVariable double amount) {
        boolean flg = accountService.withdrawFromAccount(id, amount);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR);
    }

    @PostMapping("/transfer/from_id={from_id}&to_id={to_id}&amount={amount}")
    public Result transfer(@PathVariable String from_id, @PathVariable String to_id, @PathVariable double amount) {
        boolean flg = accountService.transfer(from_id, to_id, amount);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR);
    }

    @PostMapping("/queryBalance/id={id}")
    public Result queryBalance(@PathVariable String id) {
        double balance = accountService.queryBalance(id);
        return new Result(balance, Code.GET_OK);
    }

    @PostMapping("/changePassword/id={id}&password={password}")
    public Result changePassword(@PathVariable String id, @PathVariable String password) {
        boolean flg = accountService.changePassword(id, password);
        return new Result(flg, flg ? Code.UPDATE_OK : Code.UPDATE_ERR);
    }

    @PostMapping("/checkPassword/id={id}&password={password}")
    public Result checkPassword(@PathVariable String id, @PathVariable String password) {
        boolean flg = password.equals(accountService.queryPassword(id));
        return new Result(flg, flg ? Code.GET_OK : Code.GET_ERR, flg ? "密码验证通过！" : "密码不正确！请重试！");
    }

    @PostMapping("/queryOwner/id={id}")
    public Result queryOwner(@PathVariable String id) {
        String owner = accountService.queryOwner(id);
        return new Result(owner, Code.GET_OK);
    }

    @PostMapping("/queryType/id={id}")
    public Result queryType(@PathVariable String id) {
        String type = accountService.queryType(id);
        return new Result(type, Code.GET_OK);
    }

    @PostMapping("/deleteAccount/id={id}")
    public Result deleteAccount(@PathVariable String id) {
        boolean flg = accountService.deleteAccount(id);
        return new Result(flg, flg ? Code.DELETE_OK : Code.DELETE_ERR);
    }
}
