package com.hkrt.skinplugin.skin;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.hkrt.skinplugin.skin.attr.SkinView;
import com.hkrt.skinplugin.skin.callback.ISkinChangeListener;
import com.hkrt.skinplugin.skin.config.SkinConfig;
import com.hkrt.skinplugin.skin.config.SkinPreUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SkinManager {

    public static SkinManager mInstance;

    private SkinResource mSkinResource;

    private Context mContext;

    private Map<ISkinChangeListener,List<SkinView>> mSkinViews = new HashMap<>();

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


        //每一次打开应用都会先执行这里，防止皮肤被恶意删除，做一些措施
        String currentPath = SkinPreUtils.getInstance(context).getSkinPath();
        File file = new File(currentPath);
        if(!file.exists()){
            //皮肤不存在，清空皮肤信息
            SkinPreUtils.getInstance(context).clearSKinInfo();
            return;
        }

        //最好做一下，看看能不能获取到包名
        String packageName = mContext.getPackageManager().getPackageArchiveInfo(
                currentPath, PackageManager.GET_ACTIVITIES).packageName;

        if(TextUtils.isEmpty(packageName)){
            SkinPreUtils.getInstance(context).clearSKinInfo();
            return ;
        }

        mSkinResource = new SkinResource(context,currentPath);

    }

    public int loadSkin(String skinPath){
        File file = new File(skinPath);

        if(!file.exists()){
            return SkinConfig.SKIN_NO_EXIST;
        }

        //最好做一下，看看能不能获取到包名
        String packageName = mContext.getPackageManager().getPackageArchiveInfo(
                skinPath, PackageManager.GET_ACTIVITIES).packageName;
        if(TextUtils.isEmpty(packageName)){
            return SkinConfig.SKIN_FILE_ERROR;
        }

        //拿到当前的皮肤，对比，如果一样就return
        String currentPath = SkinPreUtils.getInstance(mContext).getSkinPath();
        if(currentPath.equals(skinPath)){
            return SkinConfig.SKIN_NO_CHANGE;
        }
        //初始化资源管理
        mSkinResource = new SkinResource(mContext,skinPath);
        //改变皮肤
        changeSkin();

        //保存皮肤状态
        saveSkinStatus(skinPath);

        return SkinConfig.SKIN_SUCCESS ;
    }

    private void saveSkinStatus(String skinPath) {
        SkinPreUtils.getInstance(mContext).saveSkinPath(skinPath);
    }

    //改变皮肤
    private void changeSkin() {
        //改变皮肤
        Set<ISkinChangeListener> keys = mSkinViews.keySet();
        for (ISkinChangeListener activity : keys) {
            List<SkinView> skinViews = mSkinViews.get(activity);
            for (SkinView skinView : skinViews) {
                skinView.skin();
            }

            activity.changeSkin(mSkinResource);
        }
    }

    public SkinResource getSkinResources() {
        return mSkinResource;
    }

    /**
     * 检查要不要换肤
     */
    public void checkChangeSkin(SkinView skinView){
        String currentPath = SkinPreUtils.getInstance(mContext).getSkinPath();

        if(!TextUtils.isEmpty(currentPath)){
            skinView.skin();
        }
    }

    /**
     * 恢复默认皮肤
     */
    public int restoreDefault(){
        //判断当前有没有皮肤，如果没有皮肤，直接返回
         String currentPath = SkinPreUtils.getInstance(mContext).getSkinPath();
        if(TextUtils.isEmpty(currentPath)){
            return SkinConfig.SKIN_NO_CHANGE;
        }
        String skinPath = mContext.getPackageResourcePath();
        mSkinResource = new SkinResource(mContext,skinPath);
        changeSkin();

        //清空preferences中的信息
        SkinPreUtils.getInstance(mContext).clearSKinInfo();
        return SkinConfig.SKIN_SUCCESS;
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
    public void register(ISkinChangeListener activity,List<SkinView> skinViews){
        mSkinViews.put(activity,skinViews);
    }

    public void unRegister(ISkinChangeListener activity){
//        mSkinViews.remove(activity);
    }

    /**
     * 获取当前皮肤资源
     */
    public SkinResource getSkinResource(){
        return mSkinResource;
    }
}
