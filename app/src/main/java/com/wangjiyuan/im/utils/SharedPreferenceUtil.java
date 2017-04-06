package com.wangjiyuan.im.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

/**
 * Created by wjy on 2015/9/6.
 */
public class SharedPreferenceUtil {
    /**
     *  获取SharedPreferences对象
     *
     * @param context 上下文
     * @return SharedPreferences对象
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     *  用于移除指定的key
     *
     * @param context 上下问对象
     * @param key     键
     */

    public static void remove(Context context, String key) {
        getSharedPreferences(context).edit().remove(key).apply();
    }

    /**
     *  传入Int型的value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putInt(Context context, String key, int value) {
        getSharedPreferences(context).edit().putInt(key, value).apply();
    }

    /**
     *  传入long型的value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putLong(Context context, String key, long value) {
        getSharedPreferences(context).edit().putLong(key, value).apply();
    }

    /**
     *  传入float型的value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putFloat(Context context, String key, float value) {
        getSharedPreferences(context).edit().putFloat(key, value).apply();
    }


    /**
     *  传入boolean型的value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    /**
     *  传入String型的value
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).apply();
    }

    /**
     *  返回key对应的值，如果没有对应值则返回默认值
     *
     * @param context  上下文
     * @param key      键
     * @param defvalue 默认值
     * @return String型
     */
    public static String getString(Context context, String key, String defvalue) {

        return getSharedPreferences(context).getString(key, defvalue);

    }

    /**
     * 返回key对应的值，如果没有对应值则返回默认值
     *
     * @param context  上下文
     * @param key      键
     * @param defvalue 默认值
     * @return int型
     */
    public static int getInt(Context context, String key, int defvalue) {

        return getSharedPreferences(context).getInt(key, defvalue);

    }

    /**
     * 返回key对应的值，如果没有对应值则返回默认值
     *
     * @param context  上下文
     * @param key      键
     * @param defvalue 默认值
     * @return boolean型
     */
    public static boolean getBoolean(Context context, String key, boolean defvalue) {

        return getSharedPreferences(context).getBoolean(key, defvalue);

    }

    /**
     *  返回key对应的值，如果没有对应值则返回默认值
     *
     * @param context  上下文
     * @param key      键
     * @param defvalue 默认值
     * @return float型
     */
    public static float getFloat(Context context, String key, float defvalue) {

        return getSharedPreferences(context).getFloat(key, defvalue);

    }

    /**
     *  返回key对应的值，如果没有对应值则返回默认值
     *
     * @param context  上下文
     * @param key      键
     * @param defvalue 默认值
     * @return long型
     */
    public static long getLong(Context context, String key, long defvalue) {

        return getSharedPreferences(context).getLong(key, defvalue);

    }

    /**
     *  以Map集合返回所有的key和vaule
     *
     * @param context  上下文
     *
     *
     * @return Map<String,?>
     */
    public static Map<String, ?> get(Context context) {

        return getSharedPreferences(context).getAll();

    }

}
