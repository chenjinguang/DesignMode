package com.hkrt.designmode.sample2;

import android.os.Bundle;

import com.hkrt.designmode.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonEat personEat = new PersonEat();
        TeacherEat teacherEat = new TeacherEat(personEat);
        teacherEat.eat();
    }
}