package com.example.moneysave.call_backs;

import com.example.moneysave.Objects.Account;

public interface CreateAndUpdateAccount extends ServerCallback{
    void createOkUpdateBegin(Account account);
}
