package component

import com.example.kotlintutorial.DaggerSampleActivity
import dagger.BindsInstance
import dagger.Component
import modules.PetrolEngineModule
import modules.WheelsModule
import pojos.Car
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [PetrolEngineModule::class, WheelsModule::class])
interface CarComponent {
    fun getCar():Car
    fun inject(mainActivity2: DaggerSampleActivity)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun mileage(@Named("mileage") mileage:Int):Builder

        @BindsInstance
        fun engineCapacity(@Named("engineCapacity") engineCapacity:Int):Builder

        fun build():CarComponent
    }
}