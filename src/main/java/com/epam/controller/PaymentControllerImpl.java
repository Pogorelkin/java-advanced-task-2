package com.epam.controller;

import com.epam.exceptions.InsufficientFundsException;
import com.epam.service.DepositService;

public class PaymentControllerImpl implements PaymentController {
    private DepositService depositService;

    public PaymentControllerImpl(DepositService depositService) {
        this.depositService = depositService;
    }

    @Override
    public synchronized void deposit(Long userIdSender, Long userIdReceiver, Long amount) throws InsufficientFundsException {
        depositService.deposit(userIdSender, userIdReceiver, amount);
    }
}
