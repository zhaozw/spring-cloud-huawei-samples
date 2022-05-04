package com.huaweicloud.samples.domain;

import java.io.Serializable;

public class AccountStorage implements Serializable {
    private Account account;
    private Storage storage;

    public AccountStorage(Account account, Storage storage) {
        this.account = account;
        this.storage = storage;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
