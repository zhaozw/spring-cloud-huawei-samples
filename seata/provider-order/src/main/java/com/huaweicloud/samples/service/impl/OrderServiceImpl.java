package com.huaweicloud.samples.service.impl;

import com.huaweicloud.samples.dao.OrderDao;
import com.huaweicloud.samples.domain.Account;
import com.huaweicloud.samples.domain.AccountStorage;
import com.huaweicloud.samples.domain.Order;
import com.huaweicloud.samples.domain.Storage;
import com.huaweicloud.samples.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private OrderDao orderDao;

    @Override
    //@GlobalTransactional(name = "seata-create-order", rollbackFor = Exception.class)
    @GlobalTransactional(rollbackFor = Exception.class)
    public void createSuccessOrder(Order order,boolean success) {
        log.info("--------->开始新建订单");
        //1 新建订单
        orderDao.create(order);
        //2 扣减库存
        log.info("------------->订单微服务开始调用库存,做扣减Count");
        String result = restTemplate.getForObject("http://seata-provider-storage/decrease?productId={1}&count={2}", String.class, order.getProductId(), order.getCount());
        log.info("------------->" + result);
        if (!result.equals("1")){
            throw new  RuntimeException("扣减库存失败");
        }
        log.info("------------->订单微服务开始调用库存,做扣减end");
        //3 扣减账户
        log.info("------------->订单微服务开始调用账户,做扣减Money");
        result = restTemplate.getForObject("http://seata-provider-account/decrease?userId={1}&money={2}&success={3}", String.class, order.getUserId(), order.getMoney(),success);
        log.info("------------->" + result);
        if (!result.equals("1")){
            throw new  RuntimeException("扣减账户失败");
        }
        log.info("------------->订单微服务开始调用账户,做扣减end");

        //4 修改订单状态
        log.info("------------->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------------->修改订单状态结束");

        log.info("------------->下订单结束了");
    }

    @Override public AccountStorage getAccountStorage(Long userId, Long productId) {
        Account account = restTemplate.getForObject("http://seata-provider-account/getAccount?userId={1}", Account.class, userId);

        Storage storage = restTemplate.getForObject("http://seata-provider-storage/getStorage?productId={1}", Storage.class, productId);

        return new AccountStorage(account,storage);
    }
}
