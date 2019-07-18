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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        IOAccountService ioAccountService = new IOAccountServiceImpl(Paths.get("1.txt"));
        AccountService accountService = new AccountServiceImpl(ioAccountService);
        DepositService depositService = new DepositServiceImpl(accountService, ioAccountService);
        RequestService requestService = new RequestServiceImpl();
        RequestGenerator requestGenerator = new RequestGenerator();

        for (UserAccount user : userList) {
            user.setBalance(ThreadLocalRandom.current().nextLong(10000000));
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
