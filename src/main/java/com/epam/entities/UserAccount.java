package com.epam.entities;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String firstName = null;
    private String lastName= null;
    private Integer id= null;
    private Long balance= null;

    public UserAccount() {
    }

    public UserAccount(Integer id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = secondName;
    }

    public UserAccount(String firstName, String secondName, Long balance) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.balance = balance;
    }

    public UserAccount(Integer id, String firstName, String secondName, Long balance) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                " id=" + id + ' ' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
