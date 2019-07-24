package com.epam.service;

import com.epam.entities.TransferRequest;

public interface RequestService {
    TransferRequest receiveRequest() throws InterruptedException;

    public void sendRequest(TransferRequest request) throws InterruptedException;
    public int getReceivedRequestsAmount();



}
