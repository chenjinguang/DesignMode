package com.hkrt.skinplugin;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.LayoutInflaterFactory;

public abstract class  BaseSkinActivity extends BaseActivity {

    private AppCompatViewInflater mAppCompatViewInflater;

    private String TAG = "BaseSkinActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater,  this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        //拦截View的创建，获取View之后要去解析
        //1.创建View
        View view = createView(parent,name,context,attrs);

        //2.解析属性，src textColor，background 以及自定义属性


        return super.onCreateView(parent, name, context, attrs);
    }


    private View createView(View parent, String name, Context context, AttributeSet attrs) {
    }
}
