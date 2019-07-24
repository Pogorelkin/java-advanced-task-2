package com.epam.service.impl;

import com.epam.entities.TransferRequest;
import com.epam.service.RequestGenerator;
import com.epam.service.RequestService;
import com.epam.service.TransferRequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class TransferRequestSenderImpl implements TransferRequestSender {
    private RequestService requestService;
    private RequestGenerator requestGenerator;
    private TransferRequest request;
    private Logger logger = LoggerFactory.getLogger(TransferRequestSenderImpl.class);
    private int transfersAmount;

    public TransferRequestSenderImpl(RequestService requestService, RequestGenerator requestGenerator, Integer transfersAmount) {
        this.requestService = requestService;
        this.requestGenerator = requestGenerator;
        this.transfersAmount = transfersAmount;
    }

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        while (requestService.getReceivedRequestsAmount() < transfersAmount) {
            try {
                request = requestGenerator.generateRequest(ThreadLocalRandom.current().nextInt(1, 10), ThreadLocalRandom.current().nextInt(1, 10));
                requestService.sendRequest(request);
                logger.info(new StringBuilder().append("Request").append(request.toString()).append("was sent").toString());
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        }
    }
}
