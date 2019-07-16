package com.epam.entities;

public class TransferRequest {
    private Long senderId;
    private Long receiverId;
    private Long moneyAmount;

    public TransferRequest(Long senderId, Long receiverId, Long moneyAmount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.moneyAmount = moneyAmount;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
}
