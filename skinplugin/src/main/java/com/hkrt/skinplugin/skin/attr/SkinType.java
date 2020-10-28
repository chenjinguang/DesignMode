package com.hkrt.skinplugin.skin.attr;

import android.view.View;

import com.hkrt.skinplugin.skin.SkinResource;

public enum SkinType {

    TEXT_COLOR("textColor"){
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
        }
    },
    SRC("src"){
        @Override
        public void skin(View view, String resName) {

        }
    },
    BACKGROUND("background"){
        @Override
        public void skin(View view, String resName) {

        }
    };

    private SkinResource getSkinResource() {
        return new SkinResource()
    }

    private String mResName;
    SkinType(String resName){
        this.mResName = resName;
    }

    public abstract void skin(View view, String resName);

    public String getResName(){
        return  mResName;
    }


}
