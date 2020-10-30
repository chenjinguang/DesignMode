package com.hkrt.skinplugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.hkrt.skinplugin.skin.SkinManager;

import java.io.File;

public class MainActivity extends BaseSkinActivity {

    @Override
    protected void initData() {
        // 1.敲一下，不要纠结，回过头再去看

    }


    @Override
    protected void initView() {
        // 初始化 View
        // extends Activity mImageIv is ImageView
        // extends AppCompatActivity mImageView is AppCompatImageView  tint
    }

    @Override
    protected void initTitle() {
        DefaultNavigationBar navigationBar = new
                DefaultNavigationBar.Builder(this)
                .setTitle("投稿")
                .builder();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    public void skin(View view){
        // 从服务器上下载

        // 跳转
//        int id = R.layout.activity_main;
//        Log.e("SkinSupport","id === " + id);
        String SkinPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator +"red.skin";
        // 换肤
        int result = SkinManager.getInstance().loadSkin(SkinPath);
    }

    public void skin1(View view){
        // 恢复默认
        int result = SkinManager.getInstance().restoreDefault();
    }


    public void skin2(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}