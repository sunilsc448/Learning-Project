package daggerTutorial

import android.app.Person
import dagger.Binds
import dagger.Module
import daggerTutorial.binds.ISampleBind
import daggerTutorial.binds.SampleBindImpl

@Module
abstract class SampleBindModule {
    @Binds
    abstract fun getSampleBind(sampleBindImpl: SampleBindImpl):ISampleBind
}