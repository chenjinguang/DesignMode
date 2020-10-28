package com.hkrt.designmode.sample1;

public class PersonEat implements Eat {
    @Override
    public void eat() {
        System.out.println("吃饭");
    }
}
