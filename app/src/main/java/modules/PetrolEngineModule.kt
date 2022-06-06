package modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import pojos.Engine
import pojos.PetrolEngine
import javax.inject.Named

@Module
class PetrolEngineModule {
    @Provides
    fun getEngine(petrolEngine: PetrolEngine):Engine{
        return petrolEngine
    }
}