package com.epam.entities;

public class TransferRequest {
    private Integer senderId;
    private Integer receiverId;
    private Long moneyAmount;

    public TransferRequest() {
    }

    public TransferRequest(Integer senderId, Integer receiverId, Long moneyAmount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.moneyAmount = moneyAmount;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Long getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
