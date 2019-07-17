package com.epam;

import com.epam.dao.IOAccountService;
import com.epam.dao.impl.IOAccountServiceImpl;
import com.epam.entities.UserAccount;
import com.epam.service.AccountService;
import com.epam.service.DepositService;
import com.epam.service.RequestGenerator;
import com.epam.service.RequestService;
import com.epam.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        IOAccountService ioAccountService = new IOAccountServiceImpl();
        AccountService accountService = new AccountServiceImpl(ioAccountService);
        RequestService requestService = new RequestServiceImpl();
        DepositService depositService = new DepositServiceImpl(accountService, ioAccountService);
        RequestGenerator requestGenerator = new RequestGenerator();
        List<UserAccount> userList = new ArrayList<>();
        Random random = new Random();

        userList.add(new UserAccount(1L, "Ivan", "Ivanov"));
        userList.add(new UserAccount(2L, "Petr", "Petrov"));
        userList.add(new UserAccount(3L, "Pyotr", "Petrov"));
        userList.add(new UserAccount(4L, "Aleksandr", "Alexandrov"));
        userList.add(new UserAccount(5L, "Alexandr", "Aleksandrow"));
        userList.add(new UserAccount(6L, "Aleksandir", "Alexandriv"));
        userList.add(new UserAccount(7L, "Jacob", "Alban"));
        userList.add(new UserAccount(8L, "Yakov", "Yakovlev"));
        userList.add(new UserAccount(9L, "Jakov", "Baks"));
        userList.add(new UserAccount(10L, "Iakow", "Heh"));

        for (UserAccount user : userList) {
            user.setBalance(random.nextLong());
            try {
                ioAccountService.createAccountFile(user);
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        accountService.printUserSummary();

        ExecutorService service = Executors.newFixedThreadPool(20);
        service.submit(new TransferRequestReceiverImpl(requestService, depositService));
        service.submit(new TransferRequestSenderImpl(requestService, requestGenerator));

        accountService.printUserSummary();
    }
}
