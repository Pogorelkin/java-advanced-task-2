package com.epam.service;

import com.epam.exceptions.InsufficientFundsException;
import com.epam.exceptions.UserNotFoundException;

public interface DepositService {
    boolean deposit(Long userIdSender, Long userIdReceiver,  Long amount) throws InsufficientFundsException, UserNotFoundException;
}
