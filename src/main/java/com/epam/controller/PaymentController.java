package com.epam.controller;

import com.epam.exceptions.InsufficientFundsException;

public interface PaymentController {
    void deposit(Long userIdSender, Long userIdReceiver,  Long amount) throws InsufficientFundsException;
}
