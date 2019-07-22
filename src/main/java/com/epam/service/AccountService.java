package com.epam.service;

import com.epam.entities.UserAccount;

import java.io.IOException;
import java.util.List;

public interface AccountService {
    UserAccount getUser(Integer userId);

    boolean isEnoughMoney(UserAccount userAccount, Long amount);

    List<UserAccount> getUsersList();

    void printUserSummary();

    void addUserAccount(UserAccount account);

    void rewriteAccount(UserAccount userAccount);

}
