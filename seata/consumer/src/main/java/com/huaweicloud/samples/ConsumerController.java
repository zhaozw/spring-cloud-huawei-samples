/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huaweicloud.samples;

import com.huaweicloud.samples.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class ConsumerController {
  @Autowired
  private RestTemplate restTemplate;

  // consumer service which delegate the implementation to provider service.
  @GetMapping("/createSuccessOrder")
  public String createSuccessOrder() {
    Order order = new Order();
    order.setProductId((long)1);
    order.setUserId((long)1);
    order.setCount(10);
    order.setMoney(new BigDecimal(200.00));
    order.setStatus(1);
    HttpEntity<Order> request = new HttpEntity<>(order);
    return restTemplate.postForObject("http://seata-provider-order/createSuccessOrder",request, String.class);
  }
  @GetMapping("/createRollbackOrder")
  public String createRollbackOrder() {
    Order order = new Order();
    order.setProductId((long)1);
    order.setUserId((long)1);
    order.setCount(10);
    order.setMoney(new BigDecimal(200.00));
    order.setStatus(1);
    HttpEntity<Order> request = new HttpEntity<>(order);
    return restTemplate.postForObject("http://seata-provider-order/createRollbackOrder",request, String.class);
  }
}
