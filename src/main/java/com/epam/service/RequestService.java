package com.epam.service;

import com.epam.entities.TransferRequest;

public interface RequestService {
    TransferRequest receiveRequest();
    public void sendRequest(TransferRequest request);


}
