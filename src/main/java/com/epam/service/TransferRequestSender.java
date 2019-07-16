package com.epam.service;

public interface TransferRequestSender extends Runnable {
    @Override
    void run();
}