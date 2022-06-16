package services

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlintutorial.R
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {
    private var serviveIntent:Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        play_btn.setOnClickListener {
            startMusicPlayerService()
            updateTextStatus()
        }

        stop_btn.setOnClickListener {
            stopMusicPlayerService()
            updateTextStatus()
        }
    }

    private fun updateTextStatus() {
        if(isMyServiceRunning(MusicPlayerService::class.java)){
            txt_service_status?.text = "Service is Running"
        }else{
            txt_service_status.text = "Service is NOT Running"
        }
    }

    private fun stopMusicPlayerService() {
        stopService(serviveIntent)
    }

    private fun startMusicPlayerService() {
        serviveIntent = Intent(this, MusicPlayerService::class.java)
        startService(serviveIntent)
    }

    override fun onStart() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().putExtra("activityAction", "started"))
        super.onStart()
    }

    override fun onStop() {
        val intent = Intent(MUSIC_PLAYER_SERVICE_BROADCAST_ID)
        intent.putExtra("activityAction", "stopped")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        super.onStop()
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().putExtra("activityAction", "paused"))
        super.onPause()
    }

    override fun onResume() {
        val intent = Intent(MUSIC_PLAYER_SERVICE_BROADCAST_ID)
        intent.putExtra("activityAction", "resumed")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        super.onResume()
    }

    override fun onDestroy() {
        stopService(serviveIntent)
        super.onDestroy()
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        try {
            val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (service in manager.getRunningServices(Int.MAX_VALUE)) {
                if (serviceClass.name == service.service.className) {
                    return true
                }
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }

    companion object{
        val MUSIC_PLAYER_SERVICE_BROADCAST_ID = "custom-media-player-broadcast"
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}