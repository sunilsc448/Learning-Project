package RXJava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintutorial.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava.*
import pojos.Task

class RXJavaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)

       Observable.fromArray(DataSourceTasks.getTasks()).
       subscribeOn(Schedulers.newThread()).
       observeOn(Schedulers.io()).
       subscribeWith(object : Observer<List<Task>>{
           override fun onSubscribe(d: Disposable) {
               TODO("Not yet implemented")
           }

           override fun onNext(t: List<Task>) {
               TODO("Not yet implemented")
           }

           override fun onError(e: Throwable) {
               TODO("Not yet implemented")
           }

           override fun onComplete() {
               TODO("Not yet implemented")
           }

       })
    }
}