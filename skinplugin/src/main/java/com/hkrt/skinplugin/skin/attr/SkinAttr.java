package com.hkrt.skinplugin.skin.attr;

import android.view.View;

public class SkinAttr {

    private String mResName;
    private SkinType mSkinType;

    public SkinAttr(String mResName, SkinType mSkinType) {
        this.mResName = mResName;
        this.mSkinType = mSkinType;
    }

    public void skin(View view){
        mSkinType.skin(view,mResName);
    }
}
