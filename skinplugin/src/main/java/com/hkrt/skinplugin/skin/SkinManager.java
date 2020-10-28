package com.hkrt.skinplugin.skin;

import android.app.Activity;
import android.content.Context;

import com.hkrt.skinplugin.skin.attr.SkinView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SkinManager {

    public static SkinManager mInstance;

    private SkinResource mSkinResource;

    private Context mContext;

    private Map<Activity,List<SkinView>> mSkinViews = new HashMap<>();

    static {
        mInstance = new SkinManager();
    }

    private SkinManager(){

    }

    public static SkinManager getInstance(){
        return mInstance;
    }

    public void init(Context context){
        mContext = context.getApplicationContext();
    }

    public int loadSkin(String skinPath){
        //初始化资源管理
        mSkinResource = new SkinResource(mContext,skinPath);
        //改变皮肤
        Set<Activity> keys = mSkinViews.keySet();
        for (Activity activity : keys) {
            List<SkinView> skinViews = mSkinViews.get(activity);

        }
    }
}
