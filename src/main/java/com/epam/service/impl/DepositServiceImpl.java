package com.epam.service.impl;

import com.epam.dao.IOAccountService;
import com.epam.entities.UserAccount;
import com.epam.exceptions.InsufficientFundsException;
import com.epam.exceptions.UserNotFoundException;
import com.epam.service.AccountService;
import com.epam.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DepositServiceImpl implements DepositService {
    private AccountService accountService;
    private IOAccountService ioAccountService;
    private Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);

    public DepositServiceImpl(AccountService accountService, IOAccountService ioAccountService) {
        this.accountService = accountService;
        this.ioAccountService = ioAccountService;
    }

    @Override
    public synchronized boolean deposit(Long userIdSender, Long userIdReceiver, Long amount)
            throws UserNotFoundException, InsufficientFundsException {
        UserAccount sender;
        UserAccount receiver;
        boolean isSuccessful = false;
        try {
            sender = accountService.getUser(userIdSender);
            receiver = accountService.getUser(userIdReceiver);
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw new UserNotFoundException("user not found");
        }
        if (accountService.isEnoughMoney(sender, amount)) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            try {
                ioAccountService.rewriteAccount(sender);
                ioAccountService.rewriteAccount(receiver);
            } catch (IOException exc) {
                logger.info("Couldn't rewrite accounts");
            }
            isSuccessful = true;
        } else {
            throw new InsufficientFundsException("Sender have insufficient funds");
        }
        return isSuccessful;
    }
}
