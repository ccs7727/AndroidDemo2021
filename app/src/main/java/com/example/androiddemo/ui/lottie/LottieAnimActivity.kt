package com.example.androiddemo.ui.lottie

import android.animation.Animator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.androiddemo.R


class LottieAnimActivity : AppCompatActivity() {
    private lateinit var mLottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_anim)
        initViews()
        setAnimations()
    }

    private var isPlay = true

    private fun initViews() {
        //实例化
        mLottieAnimationView = findViewById(R.id.animationView)
        //设置循环播放次数，-1表示循环不限制
        mLottieAnimationView.repeatCount = -1
        mLottieAnimationView.setOnClickListener {
            isPlay = !isPlay
            setAnimations()
        }
    }

    private fun setAnimations() {
        if (isPlay) {
            //指定动画文件，支持json/zip
            mLottieAnimationView.setAnimation("json.zip")
            //开始播放
            mLottieAnimationView.playAnimation()
        } else {
            //非动画情况下加载静态图
            mLottieAnimationView.setImageResource(R.mipmap.ic_launcher)
        }
//        mLottieAnimationView.postDelayed({
//            //暂停
//            mLottieAnimationView.pauseAnimation();
//        }, 5000)
    }

    override fun onStop() {
        super.onStop()
        mLottieAnimationView.cancelAnimation()
    }

}