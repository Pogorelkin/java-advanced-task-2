package com.epam.service.impl;

import com.epam.entities.TransferRequest;
import com.epam.service.RequestGenerator;
import com.epam.service.RequestService;
import com.epam.service.TransferRequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferRequestSenderImpl implements TransferRequestSender {
    private RequestService requestService;
    private RequestGenerator requestGenerator;
    private boolean sendRequests = true;
    private TransferRequest request;
    private Logger logger = LoggerFactory.getLogger(TransferRequestSenderImpl.class);

    public TransferRequestSenderImpl(RequestService requestService, RequestGenerator requestGenerator) {
        this.requestService = requestService;
        this.requestGenerator = requestGenerator;
    }

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        while (sendRequests) {
            try {
                if (requestService.getReceivedRequestsAmount() == 1000) {
                    stopSending();
                    break;
                }
                request = requestGenerator.generateRequest(1L);
                request.setSenderId(Long.valueOf(threadId));
                requestService.sendRequest(request);
                logger.info(new StringBuilder().append("Request").append(request.toString()).append("senr").toString());
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        }
    }

    private void stopSending() {
        setSendRequests(false);
    }

    private void setSendRequests(boolean sendRequests) {
        this.sendRequests = sendRequests;
    }
}
