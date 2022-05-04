package com.huaweicloud.samples.service;

import com.huaweicloud.samples.domain.Storage;

public interface StorageService {

    int decrease(Long productId, Integer count);
    Storage getStorage(Long productId);

}
