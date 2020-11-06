package com.hkrt.skinplugin.skin.config;

import android.content.Context;

/**
 * @author chenjinguang
 * @描述
 * @创建时间 2020/11/2
 * @修改人和其它信息
 */
public class SkinPreUtils {

    private static volatile  SkinPreUtils mInstance;

    private static Context mContext;

    private  SkinPreUtils(Context context){
        mContext = context.getApplicationContext();
    }

    public static SkinPreUtils getInstance(Context context){
        if(mInstance == null){
            synchronized (SkinPreUtils.class){
                if(mInstance == null){
                    mInstance = new SkinPreUtils(context);
                }
            }
        }
        return  mInstance;
    }

    public void saveSkinPath(String skinPath){
        mContext.getSharedPreferences(SkinConfig.SKIN_INFO_NAME,Context.MODE_PRIVATE)
                    .edit().putString(SkinConfig.SKIN_PATH_NAME,skinPath).apply();
    }

    public String getSkinPath(){
        return mContext.getSharedPreferences(SkinConfig.SKIN_INFO_NAME,Context.MODE_PRIVATE)
                    .getString(SkinConfig.SKIN_PATH_NAME,"");
    }

    public void clearSKinInfo(){
       saveSkinPath("");
    }

}
