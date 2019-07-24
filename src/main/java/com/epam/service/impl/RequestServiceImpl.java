package com.epam.service.impl;

import com.epam.entities.TransferRequest;
import com.epam.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestServiceImpl implements RequestService {
    private Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);
    private Queue<TransferRequest> queue = new ArrayBlockingQueue<>(50);
    AtomicInteger receivedRequestsAmount = new AtomicInteger(0);
    AtomicInteger sentRequestsAmount = new AtomicInteger(0);
    private int transfersAmount;

    public RequestServiceImpl(int transfersAmount) {
        this.transfersAmount = transfersAmount;
    }

    @Override
    public synchronized TransferRequest receiveRequest() throws InterruptedException {
        TransferRequest request = new TransferRequest();
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
                throw e;
            }
        }
        if (receivedRequestsAmount.get() < transfersAmount) {
                request = queue.poll();
                receivedRequestsAmount.getAndIncrement();
        }
        notifyAll();
        return request;
    }

    @Override
    public synchronized void sendRequest(TransferRequest request) throws InterruptedException {
        while (queue.size() == 50) {
            try {
                wait();
            } catch (InterruptedException exc) {
                throw exc;
            }
        }
        if (sentRequestsAmount.get() < transfersAmount) {
            queue.add(request);
            sentRequestsAmount.getAndIncrement();}
        notifyAll();
    }

    public int getReceivedRequestsAmount() {
        return receivedRequestsAmount.get();
    }

    public int getSentRequestsAmount() {
        return sentRequestsAmount.get();
    }

    public Queue<TransferRequest> getQueue() {
        return queue;
    }
}
