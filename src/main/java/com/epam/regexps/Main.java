package com.epam.regexps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String INPUT = "src/main/resources/sample-text.txt";
    public static final String OUTPUT = "src/main/resources/found-numbers.txt";

    NumberFormat numberFormat;
    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);
        IOUtil util = new IOUtil();
        List<String> numberList = new ArrayList<>();
        PhoneNumberParser parser = new PhoneNumberParser();
        Formatter formatter = new Formatter();
        List<String> stringList = util.readFileByPath(INPUT);

        for (String s: stringList) {
            numberList.addAll(formatter.formatNumberList(parser.getNumberListFromString(s)));
        }
        logger.info(numberList.toString());
        util.writeToFileByPath(numberList,OUTPUT);
    }
}
