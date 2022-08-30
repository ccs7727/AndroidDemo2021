package com.example.androiddemo.ui.skeleton.skeleton4

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddemo.R
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import kotlinx.android.synthetic.main.fragment_recyclerview.*

class Skeleton4ListActivity : AppCompatActivity(R.layout.fragment_recyclerview) {

    lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = RecyclerViewListItem.DEMO

        val listAdapter = RecyclerViewAdapter(items)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = listAdapter

        skeleton = list.applySkeleton(R.layout.list_item_recyclerview, items.size).apply { showSkeleton() }

        Handler(Looper.getMainLooper()).postDelayed(
            {
                skeleton.showOriginal()
            },
            3000
        )
    }

}