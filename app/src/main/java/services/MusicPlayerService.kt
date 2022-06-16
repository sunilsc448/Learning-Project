package services

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlintutorial.MainActivity
import com.example.kotlintutorial.R
import movies.view.MoviesActivity

const val  ACTION_STOP =  "stop"
class MusicPlayerService:Service() {
    private lateinit var player:MediaPlayer
    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if(intent.hasExtra("activityAction")){
                val action = intent.getStringExtra("activityAction")
                if(action.equals("paused")){
                    player.pause()
                }else if(action.equals("resumed")){
                    player.start()
                }else if(action.equals("stopped")){
                    player.stop()
                }else if(action.equals("started")){
                    //player.start()
                }
            }
        }
    }
    //Notififcation for ON-going
    private var iconNotification: Bitmap? = null
    private var notification: Notification? = null
    private var mNotificationManager: NotificationManager? = null
    private val mNotificationId = 123
    private fun generateForegroundNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intentMainLanding = Intent(this, MoviesActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intentMainLanding, 0)
            iconNotification = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            if (mNotificationManager == null) {
                mNotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                assert(mNotificationManager != null)
                mNotificationManager?.createNotificationChannelGroup(
                    NotificationChannelGroup("chats_group", "Chats")
                )
                val notificationChannel =
                    NotificationChannel("service_channel", "Service Notifications",
                        NotificationManager.IMPORTANCE_MIN)
                notificationChannel.enableLights(false)
                notificationChannel.lockscreenVisibility = Notification.VISIBILITY_SECRET
                mNotificationManager?.createNotificationChannel(notificationChannel)
            }
            val builder = NotificationCompat.Builder(this, "service_channel")

            builder.setContentTitle(StringBuilder(resources.getString(R.string.app_name)).append(" service is running").toString())
                .setTicker(StringBuilder(resources.getString(R.string.app_name)).append("service is running").toString())
                .setContentText("Touch to open") //                    , swipe down for more options.
                .setSmallIcon(R.drawable.not)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setWhen(0)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
            if (iconNotification != null) {
                builder.setLargeIcon(Bitmap.createScaledBitmap(iconNotification!!, 128, 128, false))
            }
            builder.color = resources.getColor(R.color.colorAccent)
            notification = builder.build()
            startForeground(mNotificationId, notification)
        }
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI )
        player.setLooping( true )
        stopForeground(true)
//        generateForegroundNotification()
        player.start()

        val filter = IntentFilter()
        filter.addAction(ServiceActivity.MUSIC_PLAYER_SERVICE_BROADCAST_ID)
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,filter)

        if (intent?.action != null && intent.action?.equals(ACTION_STOP, ignoreCase = true)!!) {
            stopSelf()
        }

        return START_STICKY;
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onDestroy() {
        // stopping the process
        player.stop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
        super.onDestroy();
    }
}
