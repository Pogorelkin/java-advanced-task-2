package com.epam.enums;

import java.util.concurrent.ThreadLocalRandom;

public enum SomeSurnames {
    PETROV("Petrov"),
    IVANOV("Ivanov"),
    SIDOVOR("Sidovor"),
    ALHAZIR("al-Hazir");

    private String surname;

    SomeSurnames(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public SomeSurnames getRandomSurame(){
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }
}
