package com.epam.dao;

import com.epam.entities.UserAccount;

import java.io.IOException;

public interface IOService {

    public UserAccount getUserAccountById(Long id) throws IOException;
    public void createAccountFile(UserAccount userAccount) throws IOException;
}
