package com.hkrt.skinplugin.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hkrt.skinplugin.skin.SkinManager;
import com.hkrt.skinplugin.skin.SkinResource;

public enum SkinType {

    TEXT_COLOR("textColor"){
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            ColorStateList colorByName = skinResource.getColorByName(resName);
            if(colorByName == null){
                return;
            }
            TextView textView = (TextView) view;
            textView.setTextColor(colorByName);
        }
    },
    SRC("src"){
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            Drawable drawableByName = skinResource.getDrawableByName(resName);
            if(drawableByName == null) return;
            ImageView imageView = (ImageView) view;
            imageView.setImageDrawable(drawableByName);
        }
    },
    BACKGROUND("background"){
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            Drawable drawableByName = skinResource.getDrawableByName(resName);
            if(drawableByName != null){
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(drawableByName);
            }

            ColorStateList colorByName = skinResource.getColorByName(resName);
            if(colorByName != null){
                view.setBackgroundColor(colorByName.getDefaultColor());
            }
        }
    };

    public SkinResource getSkinResource() {
        return SkinManager.getInstance().getSkinResources();
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
