package com.epam.entities;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String firstName;
    private String lastName;
    private Long id;
    private Long balance;

    public UserAccount(String firstName, String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
    }

    public UserAccount(String firstName, String secondName, Long balance) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.balance = balance;
    }

    public UserAccount(Long id, String firstName, String secondName, Long balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = secondName;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
