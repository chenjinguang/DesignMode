package com.hkrt.skinplugin;

import android.app.Application;

import com.hkrt.skinplugin.skin.SkinManager;

/**
 * @author chenjinguang
 * @描述
 * @创建时间 2020/10/30
 * @修改人和其它信息
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SkinManager.getInstance().init(this);
    }
}
