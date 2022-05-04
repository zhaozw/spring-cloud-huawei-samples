package com.huaweicloud.samples.service;

import com.huaweicloud.samples.domain.AccountStorage;
import com.huaweicloud.samples.domain.Order;

public interface OrderService {

    void createSuccessOrder(Order order,boolean success);

    AccountStorage getAccountStorage(Long userId, Long productId);
}
