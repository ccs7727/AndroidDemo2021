package com.example.androiddemo.ui.skeleton.skeleton3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddemo.R
import com.example.androiddemo.databinding.ActivityMainBinding
import com.example.androiddemo.ui.skeleton.skeleton3.profile.ListItemUtils
import com.example.androiddemo.ui.skeleton.skeleton3.profile.Profile
import com.example.androiddemo.ui.skeleton.skeleton3.profile.ProfileAdapter
import com.skydoves.androidveil.VeiledItemOnClickListener

/**
 * Developed by skydoves on 2018-10-30.
 * Copyright (c) 2018 skydoves rights reserved.
 */

class Skeleton3ListActivity :
  AppCompatActivity(),
  VeiledItemOnClickListener,
  ProfileAdapter.ProfileViewHolder.Delegate {

  private val adapter = ProfileAdapter(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // sets VeilRecyclerView's properties
    binding.veilRecyclerView.run {
      setVeilLayout(R.layout.item_profile, this@Skeleton3ListActivity)
      setAdapter(adapter)
      setLayoutManager(LinearLayoutManager(this@Skeleton3ListActivity))
      addVeiledItems(15)
    }

    // add profile times to adapter
    adapter.addProfiles(ListItemUtils.getProfiles(this))

    // delay-auto-unveil
    Handler(Looper.getMainLooper()).postDelayed(
      {
        binding.veilRecyclerView.unVeil()
      },
      3000
    )
  }

  /** OnItemClickListener by Veiled Item */
  override fun onItemClicked(pos: Int) {
    Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show()
  }

  /** OnItemClickListener by User Item */
  override fun onItemClickListener(profile: Profile) {
    startActivity(Intent(this, Skeleton3ViewActivity::class.java))
  }
}
