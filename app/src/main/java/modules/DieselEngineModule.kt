package modules

import dagger.Module
import dagger.Provides
import pojos.Engine
import pojos.PetrolEngine

@Module
class PetrolEngineModule {
    @Provides
    fun getEngine(petrolEngine: PetrolEngine):Engine{
        return petrolEngine
    }
}