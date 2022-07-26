package daggerTutorial.binds

import javax.inject.Inject


class SampleBindImpl @Inject constructor(): ISampleBind{
    override fun sampleMethod() {
        println("sample bind")
    }
}