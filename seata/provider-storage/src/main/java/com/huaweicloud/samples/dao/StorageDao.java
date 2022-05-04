package com.huaweicloud.samples.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.huaweicloud.samples.domain.Storage;

@Mapper
public interface StorageDao {

    int decrease(@Param("productId") Long productId, @Param("count") Integer count);

    Storage getStorageByProductId(@Param("productId") Long productId);

}
