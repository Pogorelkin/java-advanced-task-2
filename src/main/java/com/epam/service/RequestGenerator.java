package com.epam.service;

import com.epam.entities.TransferRequest;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RequestGenerator {
    private Integer sernderId;
    private Integer receiverId;
    private Long amount;

    public TransferRequest generateRequest(Integer sernderNum, Integer receiverNum) {
        receiverId = receiverNum;
        sernderId = sernderNum;
        amount = ThreadLocalRandom.current().nextLong(500);
        return new TransferRequest(sernderId,receiverId, amount);
    }
}
