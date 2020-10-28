package com.hkrt.designmode.sample1;

import android.os.Bundle;

import com.hkrt.designmode.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TeacherEat teacherEat = new TeacherEat();
        teacherEat.eat();
    }
}