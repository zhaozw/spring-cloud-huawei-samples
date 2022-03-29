package com.huaweicloud.samples.service.impl;

import com.huaweicloud.samples.dao.AccountDao;
import com.huaweicloud.samples.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Override
    public int decrease(Long userId, BigDecimal money,boolean success) {
        if (!success){

            try {
                // 模拟超时异常
                //TimeUnit.SECONDS.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new RuntimeException("模拟异常");
        }
        //accountDao.decrease(userId, money);
        //throw new RuntimeException("模拟异常");
        //int a = 1/0;
        return accountDao.decrease(userId, money);
    }
    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
