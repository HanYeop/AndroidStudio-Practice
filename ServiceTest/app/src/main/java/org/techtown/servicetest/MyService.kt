package org.techtown.servicetest

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {
    lateinit var mp : MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        // Service 객체와 통신할 때 사용
        TODO("Return the communication channel to the service.")
    }

    // 서비스가 호출되었을 때 한번만 호출
    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this,R.raw.carole)
        mp.isLooping = false // 반복재생
    }

    // 서비스가 호출될때마다 호출 (음악재생)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp.start()
        return super.onStartCommand(intent, flags, startId)
    }

    // 서비스가 종료될 때 음악 종료
    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }
}