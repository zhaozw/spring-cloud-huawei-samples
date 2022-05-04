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

import com.alibaba.fastjson.JSONObject;
import com.huaweicloud.samples.domain.AccountStorage;
import com.huaweicloud.samples.domain.Order;
import com.huaweicloud.samples.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
  @Autowired
  private OrderService orderService;

  @PostMapping("/createSuccessOrder")
  public String createSuccessOrder(@RequestBody Order order) {
    AccountStorage before = orderService.getAccountStorage(order.getUserId(),order.getProductId());
    orderService.createSuccessOrder(order,true);
    AccountStorage after = orderService.getAccountStorage(order.getUserId(),order.getProductId());
    JSONObject jo = new JSONObject();
    jo.put("before",before);
    jo.put("after",after);
    return jo.toJSONString();
  }

  @PostMapping("/createRollbackOrder")
  public String createRollbackOrder(@RequestBody Order order) {
    AccountStorage before = orderService.getAccountStorage(order.getUserId(),order.getProductId());
    try{
      orderService.createSuccessOrder(order,false);
    }catch (Exception e){

    }
    AccountStorage after = orderService.getAccountStorage(order.getUserId(),order.getProductId());
    JSONObject jo = new JSONObject();
    jo.put("before",before);
    jo.put("after",after);
    return jo.toJSONString();
  }
}
