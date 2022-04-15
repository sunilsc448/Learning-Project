package modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import pojos.Engine
import pojos.PetrolEngine

@Module
class PetrolEngineModule {
//    private var mileage:Int
//    constructor(mileage:Int){
//        this.mileage = mileage
//    }

    @Provides
    fun getEngine(petrolEngine: PetrolEngine):Engine{
        return petrolEngine
    }
}