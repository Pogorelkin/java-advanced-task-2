package com.epam.dao.impl;

import com.epam.dao.IOService;
import com.epam.entities.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOServiceImpl implements IOService {
    protected UserAccount userAccount;
    private Path path;
    private Logger logger = LoggerFactory.getLogger(IOServiceImpl.class);

    public IOServiceImpl(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public UserAccount getUserAccountById(Long id) throws IOException {
        try (FileInputStream fi = new FileInputStream(new File(id + ".txt"));
             ObjectInputStream oi = new ObjectInputStream(fi)) {
            try {
                userAccount = (UserAccount) oi.readObject();
            } catch (ClassNotFoundException e) {
                logger.info(e.getMessage());
            }
        } catch (IOException exc) {
            logger.info(exc.getMessage());
            throw new IOException("smth with IO");
        }
        return userAccount;
    }

    @Override
    public void createAccountFile(UserAccount userAccount) throws IOException {
        if (!Files.exists(Paths.get(userAccount.getId() + ".txt"))) {
            try (FileOutputStream f = new FileOutputStream(new File(userAccount.getId() + ".txt"));
                 ObjectOutputStream o = new ObjectOutputStream(f)) {
                o.writeObject(userAccount);
            }
        }
    }

}
