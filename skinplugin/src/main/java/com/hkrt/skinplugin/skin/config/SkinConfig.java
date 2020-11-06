package com.hkrt.skinplugin.skin.config;

/**
 * @author chenjinguang
 * @描述
 * @创建时间 2020/11/2
 * @修改人和其它信息
 */
public class SkinConfig {

    //sp文件名称
    public static final String SKIN_INFO_NAME = "skinInfo";

    //保存皮肤文件的路径的名称
    public static final String SKIN_PATH_NAME = "skinPath";

    //不需要换肤
    public static final int SKIN_NO_CHANGE = -1;

    //换肤成功
    public static final int SKIN_SUCCESS = 0;

    //皮肤不存在
    public static final int SKIN_NO_EXIST  = 1;

    //皮肤文件有错误，可能不是一个apk文件
    public static final int SKIN_FILE_ERROR = 2;

}
