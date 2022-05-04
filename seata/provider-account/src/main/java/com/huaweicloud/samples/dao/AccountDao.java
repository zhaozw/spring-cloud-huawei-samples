package com.huaweicloud.samples.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

import com.huaweicloud.samples.domain.Account;

@Mapper
public interface AccountDao {

    int decrease(@Param("userId") Long userId,
                 @Param("money") BigDecimal money);

    Account getAccountByUserId(@Param("userId") Long userId);

}
