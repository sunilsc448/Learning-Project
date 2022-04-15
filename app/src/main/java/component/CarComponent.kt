package ikr

import com.example.kotlintutorial.DaggerSampleActivity
import dagger.Component
import modules.DieselEngineModule
import modules.PetrolEngineModule
import pojos.Car

@Component(modules = [PetrolEngineModule::class])
interface CarComponent {
    fun getCar():Car
    fun inject(mainActivity2: DaggerSampleActivity)
}