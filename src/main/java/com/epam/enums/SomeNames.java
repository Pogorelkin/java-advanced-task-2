package com.epam.enums;

import java.util.concurrent.ThreadLocalRandom;

public enum  SomeNames {
    ALEXIY("Alexiy"),
    ALEKSANDIR("Alexandir"),
    BALERA("Balera"),
    PYOTR("Pyotr"),
    YAKOV("Yakov");

    SomeNames(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public SomeNames getRandomName(){
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }
}
