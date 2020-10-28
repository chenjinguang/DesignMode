package com.hkrt.designmode.sample2;

public class TeacherEat implements Eat{
    Eat eat;
    public TeacherEat(PersonEat eat) {
        this.eat = eat;
    }

    @Override
    public void eat() {
        System.out.println("去教职工食堂");
        eat.eat();
        System.out.println("回教室上课");
    }
}
