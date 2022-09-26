/*
 * Copyright (C) 2018 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androiddemo.ui.skeleton.skeleton3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddemo.databinding.ActivityView2Binding
import com.example.androiddemo.ui.skeleton.skeleton1.adapter.NewsAdapter

/**
 * Developed by skydoves on 2018-10-30.
 * Copyright (c) 2018 skydoves rights reserved.
 */

class Skeleton3ViewActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityView2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailVeilLayoutBody.shimmer = ShimmerUtils.getGrayShimmer(this)

        val adapter = NewsAdapter()
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = adapter

        // delay-auto-unveil
        Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.detailVeilLayoutBody.unVeil()
            },
            3000
        )
    }
}
