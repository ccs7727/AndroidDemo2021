package com.example.androiddemo.ui.watermark

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddemo.R
import kotlin.collections.ArrayList


class WaterMarkActivity : AppCompatActivity() {
    private lateinit var decorateImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watermark)
        initView()
    }

    private fun initView() {
        decorateImageView = findViewById(R.id.imageView)

        val labels: MutableList<String> = ArrayList()
        labels.add("应急多方通话系统")
        decorateImageView.background = WaterMarkBg(this, labels, -45, 13)
    }

}