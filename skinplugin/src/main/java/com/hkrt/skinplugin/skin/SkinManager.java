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
            for (SkinView skinView : skinViews) {
                skinView.skin();
            }
        }

        return 0 ;
    }

    public SkinResource getSkinResources() {
        return mSkinResource;
    }

    /**
     * 恢复默认皮肤
     */
    public int restoreDefault(){
        return 0;
    }

    /**
     * 通过activity 获取skinViews
     */
    public List<SkinView> getSkinViews(Activity activity){
        return mSkinViews.get(activity);
    }

    /**
     * 注册
     *
     */
    public void register(Activity activity,List<SkinView> skinViews){
        mSkinViews.put(activity,skinViews);
    }

    /**
     * 获取当前皮肤资源
     */
    public SkinResource getSkinResource(){
        return mSkinResource;
    }
}
