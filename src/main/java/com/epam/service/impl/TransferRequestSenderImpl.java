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

    public TransferRequestSenderImpl(RequestService requestService, RequestGenerator requestGenerator) {
        this.requestService = requestService;
        this.requestGenerator = requestGenerator;
    }

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        while (requestService.getReceivedRequestsAmount() < 1000) {
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
