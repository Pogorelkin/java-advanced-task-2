package com.epam.service;

import com.epam.entities.TransferRequest;

import java.util.Random;

public class RequestGenerator {
    private Long sernderId;
    private Long receiverId;
    private Long amount;
    private Random random = new Random();

    public TransferRequest generateRequest(Long receiverNum) {
        receiverId = receiverNum;
        sernderId = Long.valueOf(random.nextInt(10) + 1);
        amount = Long.valueOf(random.nextInt(1000) + 1);
        return new TransferRequest(sernderId,receiverId, amount);
    }
}
