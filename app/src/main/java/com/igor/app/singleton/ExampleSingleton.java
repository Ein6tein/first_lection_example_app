package com.igor.app.singleton;

public class ExampleSingleton {

    private static ExampleSingleton sInstance;

    private ExampleSingleton() {}

    public static ExampleSingleton getInstance() {
        if (sInstance == null) {
            sInstance = new ExampleSingleton();
        }

        return sInstance;
    }
}
