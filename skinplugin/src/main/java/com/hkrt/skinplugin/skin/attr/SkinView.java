package com.hkrt.skinplugin.skin.attr;

import android.view.View;

import java.util.List;

public class SkinView {

    private View mView;

    private List<SkinAttr> mAttrs;

    public SkinView(View view,List<SkinAttr> attrs){
        mView = view;
        mAttrs = attrs;
    }

    public void skin(){
        for (SkinAttr mAttr : mAttrs) {
            mAttr.skin(mView);
        }
    }
}
