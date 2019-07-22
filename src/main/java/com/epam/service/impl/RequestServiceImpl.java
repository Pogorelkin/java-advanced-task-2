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
        if (receivedRequestsAmount.get() < 1000) {
            request = queue.poll();
            receivedRequestsAmount.getAndIncrement();
        }
        notify();
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
        if (sentRequestsAmount.get() < 1000) {
            queue.add(request);
            sentRequestsAmount.getAndIncrement();
        }
        notify();
    }

    public synchronized int getReceivedRequestsAmount() {
        return receivedRequestsAmount.get();
    }
}
