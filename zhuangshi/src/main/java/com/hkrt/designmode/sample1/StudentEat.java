package com.hkrt.designmode.sample1;

public class StudentEat implements Eat {

    @Override
    public void eat() {
        System.out.println("去学生食堂");
        System.out.println("吃饭");
        System.out.println("回教室上课");
    }
}
