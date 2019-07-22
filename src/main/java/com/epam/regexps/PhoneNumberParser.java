package com.epam.regexps;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberParser {
    public List<String> getNumberListFromString(String str) {
        Pattern pattern = Pattern.compile("\\+\\d\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}");
        Matcher matcher = pattern.matcher(str);
        List<String> foundNumbers = new ArrayList<>();
        while (matcher.find()) {
            foundNumbers.add(matcher.group());
        }
        return foundNumbers;
    }
}
