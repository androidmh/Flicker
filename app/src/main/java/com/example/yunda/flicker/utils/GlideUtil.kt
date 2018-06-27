package com.example.yunda.flicker.utils

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * Author by HDM, Email menghedianmo@163.com, Date on 2018/4/10.
 * PS: Not easy to write code, please indicate.
 */
object GlideUtil {
    //glide相关设置
    var options: RequestOptions = RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .fitCenter()

    /**
     * @param activity 上下文
     * @param url 加载地址
     * @param imageView 加载imageview
     * 普通加载图片
     */
    fun loadImg(activity: Context, url: Uri, imageView: ImageView) {
        Glide.with(activity)
                .load(url)
                .apply(options)
                .transition(DrawableTransitionOptions().crossFade(500))
                .into(imageView)
    }

    /**
     * @param activity 上下文
     * @param url 加载地址
     * @param imageView 加载imageview
     * 模糊加载图片
     */
    fun loadvagueImg(activity: Activity, url: Uri, imageView: ImageView) {
        Glide.with(activity)
                .load(url)
                .apply(options)
                .apply(bitmapTransform(BlurTransformation(23,8)))
                .transition(DrawableTransitionOptions().crossFade(500))
                .into(imageView)
    }
}