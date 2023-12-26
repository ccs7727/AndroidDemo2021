package com.example.androiddemo.ui.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddemo.R
import java.util.*

class RecyclerViewListActivity : AppCompatActivity() {
    var hRecyclerView: HRecyclerView? = null
    private var mDataModels: ArrayList<CoinInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hor_list)
        hRecyclerView = findViewById(R.id.id_hrecyclerview)
        getData()
    }

    private fun getData() {
        mDataModels = ArrayList()
        for (i in 0..9999) {
            val coinInfo = CoinInfo()
            coinInfo.name = "USDT"
            coinInfo.priceLast = "20.0"
            coinInfo.riseRate24 = "0.2"
            coinInfo.vol24 = "10020"
            coinInfo.close = "22.2"
            coinInfo.open = "40.0"
            coinInfo.bid = "33.2"
            coinInfo.ask = "19.0"
            coinInfo.amountPercent = "33.3%"
            mDataModels!!.add(coinInfo)
        }

        hRecyclerView?.setHeaderListData("名次", resources.getStringArray(R.array.right_title_name))

        val adapter = CoinAdapter(this, mDataModels, R.layout.item_layout, object : CommonViewHolder.onItemCommonClickListener {
            override fun onItemClickListener(position: Int) {
                Toast.makeText(this@RecyclerViewListActivity, "position--->$position", Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClickListener(position: Int) {}
        })

        hRecyclerView?.setAdapter(adapter)
    }
}