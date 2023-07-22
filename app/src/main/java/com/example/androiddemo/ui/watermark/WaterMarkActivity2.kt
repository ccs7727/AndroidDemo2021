package com.example.androiddemo.ui.watermark

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddemo.R


class WaterMarkActivity2 : AppCompatActivity() {
    private lateinit var decorateImageView: WatermarkView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watermark2)
        initView()
        initDecorateSpinner()
    }

    private fun initView() {
        decorateImageView = findViewById(R.id.imageView)

    }

    private val decorateNameArray = arrayOf("无装饰", "文字", "图片水印", "相框")
    private fun initDecorateSpinner() {
        val arrayAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, decorateNameArray
        )
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.prompt = "请选择装饰类型"
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                showResult(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinner.setSelection(0)
    }

    private fun showResult(position: Int) {
        when (position) {
            0 -> decorateImageView.showNone()
            1 -> decorateImageView.showText("多方通话系统", true)
            2 -> {
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img2)
                decorateImageView.showLogo(bitmap, true)
            }
            3 -> {
//                val frame = BitmapFactory.decodeResource(resources, R.mipmap.photo_frame3)
//                decorateImageView.showFrame(frame, true)
            }
            else -> {}
        }
    }
}