package org.techtown.foregroundservicetest

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyService : Service() {
    lateinit var mp : MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this,R.raw.carole)
        mp.isLooping = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp.start()

        val notificationIntent = Intent(this,MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)

        val notification = NotificationCompat.Builder(this, "default").apply {
            setContentTitle("Music Player")
            setContentText("음악이 재생중입니다.")
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentIntent(pendingIntent) // 알림 클릭 시 메인액티비티로 돌아옴
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val manager : NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            /*
            1. IMPORTANCE_HIGH = 알림음이 울리고 헤드업 알림으로 표시
            2. IMPORTANCE_DEFAULT = 알림음 울림
            3. IMPORTANCE_LOW = 알림음 없음
            4. IMPORTANCE_MIN = 알림음 없고 상태줄 표시 X
             */
            manager.createNotificationChannel(NotificationChannel("default","기본 채널",NotificationManager.IMPORTANCE_LOW))
        }

        // id 는 0이 아니어야함
        startForeground(1,notification.build())

        /*
        1. START_STICKY = Service 가 재시작될 때 null intent 전달
        2. START_NOT_STICKY = Service 가 재시작되지 않음
        3. START_REDELIVER_INTENT = Service 가 재시작될 때 이전에 전달했던 intent 전달
         */
        return START_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }
}