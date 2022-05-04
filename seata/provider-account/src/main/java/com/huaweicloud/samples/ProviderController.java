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

import com.huaweicloud.samples.domain.Account;
import com.huaweicloud.samples.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ProviderController {
  @Autowired
  private AccountService accountService;
  // a very simple service to echo the request parameter
  @GetMapping("/decrease")
  public String decrease(@RequestParam("userId") Long userId,@RequestParam("money") BigDecimal money,@RequestParam("success") boolean success) {
    accountService.decrease(userId,money,success);
    return "1";
  }

  @GetMapping("/getAccount")
  public Account getAccount(@RequestParam("userId") Long userId) {
    return accountService.getAccount(userId);
  }
}
