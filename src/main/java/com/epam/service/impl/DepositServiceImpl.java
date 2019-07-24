package com.epam.service.impl;

import com.epam.entities.UserAccount;
import com.epam.exceptions.InsufficientFundsException;
import com.epam.service.AccountService;
import com.epam.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepositServiceImpl implements DepositService {
    private AccountService accountService;
    private Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);

    public DepositServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public synchronized boolean deposit(Integer userIdSender, Integer userIdReceiver, Long amount)
            throws InsufficientFundsException {
        UserAccount sender;
        UserAccount receiver;
        boolean isSuccessful = false;
        sender = accountService.getUser(userIdSender);
        receiver = accountService.getUser(userIdReceiver);
        if (accountService.isEnoughMoney(sender, amount)) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            accountService.rewriteAccount(sender);
            accountService.rewriteAccount(receiver);
            isSuccessful = true;
        } else {
            throw new InsufficientFundsException("Sender have insufficient funds");
        }
        return isSuccessful;
    }
}
