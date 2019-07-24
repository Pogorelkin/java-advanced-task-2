package com.epam.controller;

import com.epam.exceptions.InsufficientFundsException;

public interface PaymentController {
    void deposit(Integer userIdSender, Integer userIdReceiver,  Long amount) throws InsufficientFundsException;
}
