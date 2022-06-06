package modules

import dagger.Module
import dagger.Provides
import pojos.DieselEngine
import pojos.Engine
import pojos.PetrolEngine
import javax.inject.Inject
import javax.inject.Named

@Module
class DieselEngineModule {
    @Provides
    fun getEngine(dieselEngine: DieselEngine):Engine{
        return dieselEngine
    }
}