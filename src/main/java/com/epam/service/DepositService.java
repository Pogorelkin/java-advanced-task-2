package com.epam.service;

import com.epam.exceptions.InsufficientFundsException;
import com.epam.exceptions.UserNotFoundException;

public interface DepositService {
    boolean deposit(Integer userIdSender, Integer userIdReceiver,  Long amount) throws InsufficientFundsException, UserNotFoundException;
}
