package com.huaweicloud.samples.service;

import java.math.BigDecimal;

public interface AccountService {

    int decrease(Long userId, BigDecimal money,boolean success);

}
