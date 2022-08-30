package com.example.androiddemo.ui.skeleton.skeleton4

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddemo.R
import com.faltenreich.skeletonlayout.Skeleton
import kotlinx.android.synthetic.main.fragment_viewgroup.*
import kotlinx.android.synthetic.main.list_item_recyclerview.*

class Skeleton4ViewActivity : AppCompatActivity(R.layout.fragment_viewgroup) {

    lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item = RecyclerViewListItem.DEMO.first()
        wallpaperView.setImageResource(item.wallpaperResId)
        avatarView.setImageResource(item.avatarResId)
        titleView.setText(item.titleResId)
        descriptionView.setText(item.descriptionResId)

        skeleton = skeletonLayout.apply { showSkeleton() }

        Handler(Looper.getMainLooper()).postDelayed(
            {
                skeleton.showOriginal()
            },
            3000
        )
    }

}