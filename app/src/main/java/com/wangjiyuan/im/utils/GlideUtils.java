package com.wangjiyuan.im.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by wjy on 2016/2/27.
 */

public class GlideUtils {
    /**
     * 加载圆形图像
     *
     * @param context     上下文
     * @param url         图片地址
     * @param imageView   目标view
     * @param placeholder 默认图片
     */
    public static void loadCircleImage(final Context context, String url, final ImageView imageView, int placeholder) {
        Log.d("Asa", "loadCircleImage: aaa");
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(placeholder)
                .centerCrop()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * 加载圆形图像
     *
     * @param context     上下文
     * @param url         图片地址
     * @param imageView   目标view
     * @param placeholder 默认图片
     */
    public static void loadDefaultImage(final Context context, String url, final ImageView imageView, int placeholder) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(placeholder)
                .fitCenter()
                .into(imageView);
    }
}
