package com.hkrt.designmode.sample2;

public class StudentEat implements Eat{

    Eat eat;

    public StudentEat(PersonEat eat) {
        this.eat = eat;
    }

    @Override
    public void eat() {
        System.out.println("去学生食堂");
        eat.eat();
        System.out.println("回教室上课");
    }
}
