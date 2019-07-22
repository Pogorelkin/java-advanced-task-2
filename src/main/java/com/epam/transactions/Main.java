package com.epam.transactions;

import com.epam.dao.IOAccountService;
import com.epam.dao.InitialAccountGenerator;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        InitialAccountGenerator accountGenerator = new InitialAccountGenerator();
        AccountService accountService = new AccountServiceImpl(accountGenerator.generateAccountList());
        IOAccountService ioAccountService = new IOAccountServiceImpl(accountService);
        DepositService depositService = new DepositServiceImpl(accountService);
        RequestService requestService = new RequestServiceImpl();
        RequestGenerator requestGenerator = new RequestGenerator();
        for (UserAccount user : accountService.getUsersList()) {
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

        try {
            service.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Coundn't transfers in 60 seconds" + e);
        }

        for (UserAccount user : accountService.getUsersList()) {
            try {
                ioAccountService.rewriteAccountFile(user);
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        accountService.printUserSummary();
    }
}
