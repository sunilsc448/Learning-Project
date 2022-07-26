package daggerTutorial.adapter

import android.app.Activity
import com.squareup.picasso.Picasso
import daggerTutorial.SampleActivity
import daggerTutorial.binds.ISampleBind
import javax.inject.Inject

class SimpleAdapter @Inject constructor(private val context:SampleActivity,
                                        private val picasso: Picasso, private val iSampleBind: ISampleBind)
{
    init {
        println("inside SimpleAdapter")
    }
}