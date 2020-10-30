package com.hkrt.skinplugin;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.LayoutInflaterFactory;
import androidx.core.view.ViewCompat;

import com.hkrt.skinplugin.skin.SkinCompatViewInflater;
import com.hkrt.skinplugin.skin.SkinManager;
import com.hkrt.skinplugin.skin.SkinSupport;
import com.hkrt.skinplugin.skin.attr.SkinAttr;
import com.hkrt.skinplugin.skin.attr.SkinView;

import java.util.ArrayList;
import java.util.List;

public abstract class  BaseSkinActivity extends BaseActivity {

    private SkinCompatViewInflater mAppCompatViewInflater;

    private String TAG = "BaseSkinActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater,  this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        //拦截View的创建，获取View之后要去解析
        //1.创建View
        View view = createView(parent,name,context,attrs);

        //2.解析属性，src textColor，background 以及自定义属性
        //一个Activity 的布局肯定对应多个SkinView
        if(view != null){
            List<SkinAttr>  skinAttrList = SkinSupport.getSkinAttrs(context,attrs);
            SkinView skinView = new SkinView(view,skinAttrList);
            //统一交给skinManager管理
            manageSkin(skinView);
        }

        return view;
    }


    private void manageSkin(SkinView skinView) {
        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if(skinViews == null){
            skinViews = new ArrayList<>();
            SkinManager.getInstance().register(this,skinViews);
        }
        skinViews.add(skinView);
    }


    private View createView(View parent, String name, Context context, AttributeSet attrs) {

        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new SkinCompatViewInflater();
        }

        // We only want the View to inherit it's context if we're running pre-v21
        final boolean inheritContext = shouldInheritContext((ViewParent) parent);

        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                 true,true,true);
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == getWindow().getDecorView() || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }
}
