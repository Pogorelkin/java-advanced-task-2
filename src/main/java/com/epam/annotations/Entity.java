package com.epam.annotations;


public class Entity {

    @Secured(value = 1)
    public void first() {
    }


    @Secured(value = 1, name = "string")
    private void second() {
    }


    private void third() {
    }
}
