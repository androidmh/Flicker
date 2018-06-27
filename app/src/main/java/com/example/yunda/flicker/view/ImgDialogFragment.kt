package com.example.yunda.flicker.view

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.yunda.flicker.R
import com.example.yunda.flicker.utils.GlideUtil
import kotlinx.android.synthetic.main.dialog_img.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.*


@SuppressLint("ValidFragment")
/**
 * Created by HMH on 2017/8/17.
 */

class ImgDialogFragment(imageurl:Uri) : DialogFragment() {
    //图片路径
    private var imageurl = imageurl;

    override fun onStart() {
        super.onStart()
        if (dialog == null) {
            return
        }

        dialog.window!!.setWindowAnimations(
                R.style.DialogAnimation)
    }

    override fun onResume() {
        super.onResume()
        //动态设置dialog大小
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val v = inflater.inflate(R.layout.dialog_img, ll_dialogf)
        val iv = v.findViewById<ImageView>(R.id.iv_pic)
        val ll = v.findViewById<RelativeLayout>(R.id.ll_dialogf)
        val tv = v.findViewById<TextView>(R.id.tv)

        GlideUtil.loadvagueImg(this!!.activity!!,imageurl,iv)
        ll.setOnTouchListener(TouchListener(this!!.activity!!,iv,tv))
        return v
    }

    internal inner class TouchListener(val activity: Activity,val iv:ImageView,val textView: TextView) :  OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            if (v.id == R.id.ll_dialogf) {
                if (event.action == MotionEvent.ACTION_UP) {
                    this@ImgDialogFragment.dismiss()
                }
               else if (event.action == MotionEvent.ACTION_DOWN) {
                    GlideUtil.loadImg(activity,imageurl,iv)
                    val myCountDownTimer = MyCountDownTimer(5000, 1000,textView)
                    myCountDownTimer.start()
                }
            }
            return true
        }

    }

    private inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long,val textView: TextView) : CountDownTimer(millisInFuture, countDownInterval) {

        //计时过程
        override fun onTick(l: Long) {
            textView.setText((l / 1000).toString() + "s")
        }

        //计时完毕的方法
        override fun onFinish() {
            this@ImgDialogFragment.dismiss()
        }
    }
}
