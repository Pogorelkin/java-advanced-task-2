package com.epam.service.impl;

import com.epam.entities.TransferRequest;
import com.epam.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class RequestServiceImpl implements RequestService {
    private Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);
    private Queue<TransferRequest> queue = new ArrayBlockingQueue<>(20);
    private  int receivedRequestsAmount = 0;
    private  int sentRequestsAmount = 0;
    @Override
    public synchronized TransferRequest receiveRequest() throws InterruptedException {
        TransferRequest request = new TransferRequest();
        while (queue.size() < 1){
            try {
                wait();
            } catch (InterruptedException e){
                logger.info(e.getMessage());
                throw e;
            }
        }
        if (receivedRequestsAmount < 1000){
            request = queue.poll();
            receivedRequestsAmount++;
        }
        notify();
        return request;
    }

    @Override
    public synchronized void sendRequest(TransferRequest request) throws InterruptedException {
        while (queue.size() == 20){
            try {
                wait();
            } catch (InterruptedException exc) {
                throw exc;
            }
        }
        if (sentRequestsAmount < 1000){
            queue.add(request);
            sentRequestsAmount++;
        }
        notify();
    }

    public synchronized int getReceivedRequestsAmount() {
        return receivedRequestsAmount;
    }

    public synchronized int getSentRequestsAmount() {
        return sentRequestsAmount;
    }
}
