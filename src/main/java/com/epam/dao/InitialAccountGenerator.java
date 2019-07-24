package com.epam.dao;

import com.epam.entities.UserAccount;
import com.epam.enums.SomeNames;
import com.epam.enums.SomeSurnames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class InitialAccountGenerator {
    private static final String ACCOUNTS_FOLDER_PATH = "src/main/resources/accounts";
    public static final int ACCOUNTS_COUNT = 10;
    List<UserAccount> userAccountList = new ArrayList<>();
    private IntStream intStream = IntStream.range(1, ACCOUNTS_COUNT + 1);
    private SomeSurnames surname = SomeSurnames.IVANOV;
    private SomeNames name = SomeNames.PYOTR;


    Logger logger = LoggerFactory.getLogger(InitialAccountGenerator.class);

    private void createUserAccountFile(UserAccount userAccount) {
        try (FileOutputStream f = new FileOutputStream(new File(userAccount.getId() + ".txt"));
             ObjectOutputStream o = new ObjectOutputStream(f)) {
            o.writeObject(userAccount);
            o.flush();
        } catch (IOException e) {
            logger.info(new StringBuilder().append("Couldn't create file").append(userAccount.getId()).append(".txt").toString());
        }
    }

    public List<UserAccount> generateAccountList() {
        intStream.forEach(number ->
            userAccountList.add(
                    (new UserAccount(number,
                            name.getRandomName().getName(),
                            surname.getRandomSurname().getSurname(),
                            ThreadLocalRandom.current().nextLong(1000000L))))
        );
        return userAccountList;
    }


}
