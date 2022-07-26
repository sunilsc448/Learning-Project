package daggerTutorial.module

import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.Provides
import daggerTutorial.SampleActivity
import daggerTutorial.adapter.SimpleAdapter
import daggerTutorial.binds.ISampleBind
import daggerTutorial.binds.SampleBindImpl
import daggerTutorial.scope.SampleActivityScope

@Module
class SampleActivityModule(private val activity: SampleActivity) {
//    @SampleActivityScope
//    @Provides
//    fun getSimpleAdapter(picasso: Picasso):SimpleAdapter{
//        return SimpleAdapter(activity, picasso)
//    }

    @SampleActivityScope
    @Provides
    fun getSampleActivity() = activity
}