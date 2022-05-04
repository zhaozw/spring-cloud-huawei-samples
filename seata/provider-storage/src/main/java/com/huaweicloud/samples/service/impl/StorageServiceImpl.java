package com.huaweicloud.samples.service.impl;

import com.huaweicloud.samples.dao.StorageDao;
import com.huaweicloud.samples.domain.Storage;
import com.huaweicloud.samples.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    private StorageDao storageDao;

    @Override
    public int decrease(Long productId, Integer count) {
        return storageDao.decrease(productId, count);
    }

    @Override public Storage getStorage(Long productId) {
        return storageDao.getStorageByProductId(productId);
    }

    @Autowired
    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }
}
