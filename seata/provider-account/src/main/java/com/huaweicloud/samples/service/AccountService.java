package com.huaweicloud.samples.service;

import java.math.BigDecimal;

import com.huaweicloud.samples.domain.Account;

public interface AccountService {

    int decrease(Long userId, BigDecimal money,boolean success);

    Account getAccount(Long userId);
}
