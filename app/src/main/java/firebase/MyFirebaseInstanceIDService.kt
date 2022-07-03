package firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlintutorial.R
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseInstanceIDService : FirebaseMessagingService() {
    private val TAG = "MyFirebaseService"
    private var broadcaster: LocalBroadcastManager? = null

    override fun onCreate() {
        super.onCreate()
        broadcaster = LocalBroadcastManager.getInstance(this)
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        handleMessage(remoteMessage)
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification?.body)
            sendNotification(remoteMessage.notification?.title, remoteMessage.notification?.body);
        }
    }

    private fun handleMessage(remoteMessage: RemoteMessage) {
        val handler = Handler(Looper.getMainLooper())
        //2
        handler.post{
            remoteMessage.notification?.let {
                val intent = Intent("MyData")
                intent.putExtra("title", it.title)
                intent.putExtra("message", it.body)
                broadcaster?.sendBroadcast(intent)
            }
            Toast.makeText(baseContext, "Handling he Notification", Toast.LENGTH_LONG).show()
        }
    }

    private fun sendNotification(title: String?, message: String?) {
        val CHANNEL_ID = "notification_channel"
        val intent = Intent(this, NotificationActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //If on Oreo then notification required a notification channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Default", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID).setContentTitle(title).setContentText(message).setSmallIcon(
            R.mipmap.ic_launcher).setContentIntent(pendingIntent).setAutoCancel(true).setOnlyAlertOnce(true)
        notificationManager.notify(1, notification.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}