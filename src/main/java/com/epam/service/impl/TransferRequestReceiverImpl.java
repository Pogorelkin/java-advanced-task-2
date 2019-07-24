package com.epam.service.impl;

import com.epam.entities.TransferRequest;
import com.epam.exceptions.InsufficientFundsException;
import com.epam.service.DepositService;
import com.epam.service.RequestService;
import com.epam.service.TransferRequestReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferRequestReceiverImpl implements TransferRequestReceiver {
    private RequestService requestService;
    private TransferRequest transferRequest;
    private Logger logger = LoggerFactory.getLogger(TransferRequestReceiverImpl.class);
    private DepositService depositService;
    private int transfersAmount;

    public TransferRequestReceiverImpl(RequestService requestService, DepositService depositService, Integer transfersAmount) {
        this.requestService = requestService;
        this.depositService = depositService;
        this.transfersAmount = transfersAmount;
    }

    @Override
    public void run() {
        while (requestService.getReceivedRequestsAmount() < transfersAmount) {
            try {

                transferRequest = requestService.receiveRequest();
                depositService.deposit(transferRequest.getSenderId(), transferRequest.getReceiverId(), transferRequest.getMoneyAmount());
                logger.info(new StringBuilder().append("Request").append(transferRequest.toString()).append("was received").toString());
            } catch (InsufficientFundsException exc) {
                logger.info(exc.getMessage());
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        }
    }
}
