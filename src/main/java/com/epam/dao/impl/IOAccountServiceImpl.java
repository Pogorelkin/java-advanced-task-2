package com.epam.dao.impl;

import com.epam.dao.IOAccountService;
import com.epam.entities.UserAccount;
import com.epam.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOAccountServiceImpl implements IOAccountService {
    protected UserAccount userAccount = new UserAccount();
    private Path path;
    private Logger logger = LoggerFactory.getLogger(IOAccountServiceImpl.class);

    public IOAccountServiceImpl() {
    }

    public IOAccountServiceImpl(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean isUserExists(Long userId) throws UserNotFoundException {
        boolean exists;
        try {
            Path filePath = Paths.get(userId + ".txt");
            if (filePath.toFile().exists() && !filePath.toFile().isDirectory()) {
                exists = true;
            } else {
                throw new UserNotFoundException("User (file) not found");
            }
        } catch (UserNotFoundException e) {
            logger.info(e.getMessage());
            throw new UserNotFoundException("User (file) not found");
        }
        return exists;
    }

    @Override
    public UserAccount getUserAccountById(Long id) throws UserNotFoundException {
        if (isUserExists(id)) {
            try (FileInputStream fi = new FileInputStream(new File(id + ".txt"));
                 ObjectInputStream oi = new ObjectInputStream(fi)) {
                userAccount = (UserAccount) oi.readObject();
            } catch (ClassNotFoundException e) {
                logger.info(e.getMessage());
            } catch (IOException exc) {
                logger.info(exc.getMessage());
                throw new UserNotFoundException("smth with IO");
            }
        }
        return userAccount;
    }

    public UserAccount getUserAccountByName(String name) throws UserNotFoundException {
        try (FileInputStream fi = new FileInputStream(name);
             ObjectInputStream oi = new ObjectInputStream(fi)) {
            userAccount = (UserAccount) oi.readObject();
        } catch (ClassNotFoundException e) {
            logger.info(e.getMessage());
        } catch (IOException exc) {
            logger.info(exc.getMessage());
            throw new UserNotFoundException("smth with IO");
        }
        return userAccount;
    }

    @Override
    public void createAccountFile(UserAccount userAccount) throws IOException {
        Path filePath = Paths.get(userAccount.getId() + ".txt");
        if (!filePath.toFile().exists()) {
            try (FileOutputStream f = new FileOutputStream(new File(userAccount.getId() + ".txt"));
                 ObjectOutputStream o = new ObjectOutputStream(f)) {
                o.writeObject(userAccount);
            }
        }
    }

    @Override
    public void rewriteAccount(UserAccount userAccount) throws IOException {
        Path filePath = Paths.get(userAccount.getId() + ".txt");
        if (filePath.toFile().exists()) {
            try (FileOutputStream f = new FileOutputStream(new File(userAccount.getId() + ".txt"), false);
                 ObjectOutputStream o = new ObjectOutputStream(f)) {
                o.writeObject(userAccount);
            } catch (FileNotFoundException e) {
                logger.info(e.getMessage());
            }
        } else throw new UserNotFoundException("User not found");
    }

    @Override
    public List<String> getAccountFileNamesByPath(String path) throws IOException {
        List<String> filenames = new ArrayList<>();
        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }
        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);
        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public List<UserAccount> getAccountsAsListByNameList(List<String> filesList) throws UserNotFoundException {
        List<UserAccount> userAccountList = new ArrayList<>();
        try {
            for (String s : filesList) {
                userAccountList.add(getUserAccountByName(s));
            }
        } catch (UserNotFoundException e) {
            logger.info(e.getMessage());
        }
        return userAccountList;
    }

    @Override
    public List<UserAccount> getUsersList() throws IOException {
        try {
            return getAccountsAsListByNameList(getAccountFileNamesByPath(this.path.toString()));
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw e;
        }
    }
}
