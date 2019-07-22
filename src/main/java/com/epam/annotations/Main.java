package com.epam.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        Method[] methods = Entity.class.getDeclaredMethods();
        for (Method method : methods) {
            Secured isSecured = method.getAnnotation(Secured.class);
            logger.info(method + "/" + isSecured);
        }
    }
}
