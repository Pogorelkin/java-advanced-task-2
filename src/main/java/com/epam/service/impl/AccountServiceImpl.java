package com.epam.service.impl;

import com.epam.dao.IOAccountService;
import com.epam.entities.UserAccount;
import com.epam.exceptions.UserNotFoundException;
import com.epam.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {
    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    public AccountServiceImpl(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
    private List<UserAccount> userAccounts = new ArrayList<>();

    @Override
    public UserAccount getUser(Integer userId) {
        return userAccounts.get(userId);
    }

    @Override
    public boolean isEnoughMoney(UserAccount userAccount, Long amount) {
        boolean haveEnough = false;
        if (userAccount.getBalance() >= amount) haveEnough = true;
        return haveEnough;
    }

    @Override
    public List<UserAccount> getUsersList() {
        return this.userAccounts;
    }

    @Override
    public void printUserSummary() {
        Long sumMoney = 0L;
        for (UserAccount userAccount : userAccounts) {
            logger.info(userAccount.toString());
            sumMoney += userAccount.getBalance();
        }
        logger.info("Total money amount on accounts is " + sumMoney);
    }

    @Override
    public void addUserAccount(UserAccount account) {
        this.userAccounts.add(account);
    }

    @Override
    public void rewriteAccount(UserAccount userAccount) {
        userAccounts.set(userAccount.getId()-1, userAccount);
    }
}
