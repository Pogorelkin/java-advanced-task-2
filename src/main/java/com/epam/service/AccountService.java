package com.epam.service;

import com.epam.entities.UserAccount;

import java.io.IOException;
import java.util.List;

public interface AccountService {
    UserAccount getUser(Long userId) throws IOException;

    boolean isEnoughMoney(UserAccount userAccount, Long amount);

    List<UserAccount> getUsersList() throws IOException;

    void printSummary(List<UserAccount> list);

}
