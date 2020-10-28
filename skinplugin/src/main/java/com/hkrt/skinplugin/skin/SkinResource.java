package com.hkrt.skinplugin.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 皮肤的资源管理
 */
public class SkinResource {
    private Resources  mSkinResources;
    private String mPackageName;

    private static final String TAG = "SkinResource";

    public SkinResource(Context context, String skinPath){

        try {
            Resources superResources = context.getResources();
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method  = assetManager.getClass().getDeclaredMethod("addAssetPath",String.class);
            method.invoke(assetManager, Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "red.skin");

            mSkinResources = new Resources(assetManager,superResources.getDisplayMetrics(),superResources.getConfiguration());

            //获取skinPath的包名
            mPackageName = Objects.requireNonNull(context.getPackageManager().getPackageArchiveInfo(
                    skinPath, PackageManager.GET_ACTIVITIES)).packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过名字获取drawable
     * @param resName
     * @return
     */
    public Drawable getDrawableByName(String resName){
        try {
            int resId = mSkinResources.getIdentifier(resName,"drawable",mPackageName);
            Drawable drawable = mSkinResources.getDrawable(resId);
            return drawable;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过名字获取颜色
     * @param resName
     * @return
     */
    public ColorStateList getColorByName(String resName){
        try{
            int resId = mSkinResources.getIdentifier(resName,"color",mPackageName);
            return mSkinResources.getColorStateList(resId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
