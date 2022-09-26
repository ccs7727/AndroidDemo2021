package com.example.androiddemo.ui.skeleton.skeleton4

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddemo.R
import com.faltenreich.skeletonlayout.Skeleton
import kotlinx.android.synthetic.main.activity_detail2.*

class Skeleton4ViewActivity2 : AppCompatActivity(R.layout.activity_detail2) {

    lateinit var skeleton: Skeleton
    lateinit var skeleton2: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        skeleton = skeletonLayout.apply { showSkeleton() }

        skeleton2 = skeletonLayout2.apply { showSkeleton() }

        Handler(Looper.getMainLooper()).postDelayed(
            {
                skeleton.showOriginal()
            },
            3000
        )

        Handler(Looper.getMainLooper()).postDelayed(
            {
                skeleton2.showOriginal()
            },
            5000
        )
    }

}