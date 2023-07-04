package com.example.androiddemo

import android.app.Application
import com.fanjun.keeplive.KeepLive
import com.fanjun.keeplive.config.ForegroundNotification
import com.fanjun.keeplive.config.KeepLiveService

/**
 * Application初始化设置
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //APP保活
//        setKeepLive()
    }

    //APP保活
    private fun setKeepLive() {
        //定义前台服务的默认样式。即标题、描述和图标
        val foregroundNotification = ForegroundNotification("服务运行中", "", R.mipmap.ic_launcher)//定义前台服务的通知点击事件
        { _, _ -> run {} }
        //启动保活服务
        KeepLive.startWork(this, KeepLive.RunMode.ENERGY, foregroundNotification,//你需要保活的服务，如socket连接、定时任务等，建议不用匿名内部类的方式在这里写
            object : KeepLiveService {
                /**
                 * 运行中
                 * 由于服务可能会多次自动启动，该方法可能重复调用
                 */
                override fun onWorking() {}

                /**
                 * 服务终止
                 * 由于服务可能会被多次终止，该方法可能重复调用，需同onWorking配套使用，如注册和注销broadcast
                 */
                override fun onStop() {}
            }
        )
    }
}