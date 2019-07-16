package com.epam.service;

public interface TransferRequestReceiver extends Runnable {
    @Override
    void run();
}
