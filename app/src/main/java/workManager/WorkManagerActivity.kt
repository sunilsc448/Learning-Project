package workManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.kotlintutorial.R
import kotlinx.android.synthetic.main.activity_service.*
import kotlinx.android.synthetic.main.activity_work_manager.*
import java.util.*
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {
    private lateinit var workManager:WorkManager
    private lateinit var workRequest:OneTimeWorkRequest
    private lateinit var workRequest2:OneTimeWorkRequest
    private lateinit var workRequest3:OneTimeWorkRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        workManager = WorkManager.getInstance(applicationContext)
        workRequest = OneTimeWorkRequest.Builder(RandomNumberGeneratorJob::class.java, ).addTag("req-1"). build()
        workRequest2 = OneTimeWorkRequest.Builder(RandomNumberGeneratorJob2::class.java).addTag("req-2").build()
        workRequest3 = OneTimeWorkRequest.Builder(RandomNumberGeneratorJob3::class.java).addTag("req-3").build()

        start_job_btn.setOnClickListener {
            workManager.beginWith(workRequest).then(workRequest2).then(workRequest3).enqueue()
            workManager.beginWith(Arrays.asList(workRequest, workRequest2)).then(workRequest3).enqueue()
        }

        stop_job__btn.setOnClickListener {
            workManager.cancelWorkById(workRequest.id)
        }
    }
}