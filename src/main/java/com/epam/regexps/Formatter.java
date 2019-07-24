package com.epam.regexps;

import java.util.List;
import java.util.stream.Collectors;

public class Formatter {
    public List<String> formatNumberList(List<String> numberList){
        return numberList.stream().map(s -> s.replaceAll("[^+\\d]","")).collect(Collectors.toList());
    }
}
