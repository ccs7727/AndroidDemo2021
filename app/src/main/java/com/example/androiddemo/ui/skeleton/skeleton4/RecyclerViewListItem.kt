package com.example.androiddemo.ui.skeleton.skeleton4

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.androiddemo.R

data class RecyclerViewListItem(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    @DrawableRes val avatarResId: Int,
    @DrawableRes val wallpaperResId: Int
) {
    companion object {
        val DEMO = listOf(
            RecyclerViewListItem(R.string.user_0_name, R.string.user_0_statement, R.drawable.img1, R.drawable.img2),
            RecyclerViewListItem(R.string.user_1_name, R.string.user_1_statement, R.drawable.img1, R.drawable.img2),
            RecyclerViewListItem(R.string.user_2_name, R.string.user_2_statement, R.drawable.img1, R.drawable.img2),
        )
    }
}