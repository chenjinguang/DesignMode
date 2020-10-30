package com.hkrt.skinplugin.skin;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.hkrt.skinplugin.skin.attr.SkinAttr;
import com.hkrt.skinplugin.skin.attr.SkinType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjinguang
 * @描述 皮肤解析类
 * @创建时间 2020/10/30
 * @修改人和其它信息
 */
public class SkinSupport {

    private static final String TAG = "SkinSupport";

    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attributeSet){
        //background src color
        List<SkinAttr> skinAttrList = new ArrayList<>();
        int attributeLength = attributeSet.getAttributeCount();
        for(int i = 0 ; i < attributeLength;i++){
            String attributeName = attributeSet.getAttributeName(i);
            String attributeValue = attributeSet.getAttributeValue(i);
            Log.e(TAG,attributeName + "  --  " + attributeValue);
            //只获取skintype中的
            SkinType skinType = getSkinType(attributeName);
            if(skinType != null){
                String resName = getResName(context,attributeValue);
                if(TextUtils.isEmpty(resName)){
                    continue;
                }
                SkinAttr attr = new SkinAttr(resName,skinType);
                skinAttrList.add(attr);
            }
        }
        return skinAttrList;
    }


    /**
     * 根据属性名获取资源名
     * @param context
     * @param attributeValue
     * @return
     */
    private static String getResName(Context context, String attributeValue) {
        if(attributeValue.contains("@")){
            attributeValue = attributeValue.substring(1);

            int resId = Integer.parseInt(attributeValue);

            return  context.getResources().getResourceName(resId);
        }
        return  null;
    }


    /**
     * 通过名称获取SkinType
     * @param attributeName
     * @return
     */
    private static SkinType getSkinType(String attributeName) {
        SkinType[] skinTypes = SkinType.values();
        for (SkinType skinType : skinTypes) {
            if(skinType.getResName().equals(attributeName)){
                return skinType;
            }
        }
        return null;
    }

}
