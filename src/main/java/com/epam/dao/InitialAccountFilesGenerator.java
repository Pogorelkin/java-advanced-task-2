package com.epam.dao;

import com.epam.entities.UserAccount;
import com.epam.enums.SomeNames;
import com.epam.enums.SomeSurnames;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class InitialAccountFilesGenerator {
    private static final String ACCOUNTS_FOLDER_PATH = "src/main/resources/accounts";
    public static final long ACCOUNTS_COUNT = 10;
    List<UserAccount> userAccountList = new ArrayList<>();
    private LongStream longStream = LongStream.range(1, ACCOUNTS_COUNT + 1L);

    Logger logger = LoggerFactory.getLogger(InitialAccountFilesGenerator.class);

    private void cleanAccountDirectory() {
        try {
            FileUtils.cleanDirectory(new File(ACCOUNTS_FOLDER_PATH));
        } catch (IOException exc) {
            logger.info("Couldn't clean dir");
        }
    }

    private void createUserAccountFile(UserAccount userAccount) {
        try (FileOutputStream f = new FileOutputStream(new File(userAccount.getId() + ".txt"));
             ObjectOutputStream o = new ObjectOutputStream(f)) {
            o.writeObject(userAccount);
            o.flush();
        } catch (IOException e) {
            logger.info(new StringBuilder().append("Couldn't create file").append(userAccount.getId()).append(".txt").toString());
        }
    }

    public void generateAccounFiles() {
        cleanAccountDirectory();
        AtomicLong index = new AtomicLong();
        longStream.forEach(number -> createUserAccountFile
                (new UserAccount(number,
                        SomeNames.getRandomName(),
                        SomeSurnames.getRandomSurname(),
                        ThreadLocalRandom.current().nextLong(1000000L))));
    }


}
