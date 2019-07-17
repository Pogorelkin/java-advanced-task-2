package com.epam.service.impl;

import com.epam.dao.IOAccountService;
import com.epam.entities.UserAccount;
import com.epam.exceptions.UserNotFoundException;
import com.epam.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private IOAccountService ioAccountService;
    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);


    public AccountServiceImpl(IOAccountService ioAccountService) {
        this.ioAccountService = ioAccountService;
    }

    @Override
    public UserAccount getUser(Long userId) throws UserNotFoundException {
        UserAccount userAccount = null;
        try {
            if (ioAccountService.isUserExists(userId)) {
                userAccount = ioAccountService.getUserAccountById(userId);
            } else {
                throw new UserNotFoundException("user not found");
            }
            return userAccount;
        } catch (IOException exception) {
            logger.info(exception.getMessage());
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public boolean isEnoughMoney(UserAccount userAccount, Long amount) {
        boolean haveEnough = false;
        if (userAccount.getBalance() >= amount) haveEnough = true;
        return haveEnough;
    }

    @Override
    public List<UserAccount> getUsersList() throws IOException {
        try {
            return ioAccountService.getUsersList();
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void printSummary(List<UserAccount> list) {
        Long sumMoney = 0L;
        for (UserAccount userAccount: list) {
            logger.info(userAccount.toString());
            sumMoney += userAccount.getBalance();
        }
        logger.info(String.format("Total money amount on accounts is %d", sumMoney));
    }
}
