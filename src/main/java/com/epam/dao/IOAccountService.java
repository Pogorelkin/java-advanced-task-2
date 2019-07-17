package com.epam.dao;

import com.epam.entities.UserAccount;
import com.epam.exceptions.UserNotFoundException;

import java.io.IOException;
import java.util.List;

public interface IOAccountService {

    boolean isUserExists(Long userId) throws UserNotFoundException;

    UserAccount getUserAccountById(Long id) throws IOException;

    void createAccountFile(UserAccount userAccount) throws IOException;

    void rewriteAccount(UserAccount userAccount) throws IOException;

    List<String> getAccountFileNamesByPath(String path) throws IOException;

    List<UserAccount> getAccountsAsListByNameList(List<String> filesList);

    List<UserAccount> getUsersList() throws IOException;

}
