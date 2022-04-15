package modules

import dagger.Module
import dagger.Provides
import pojos.Rims
import pojos.Tyres
import pojos.Wheels

@Module
class WheelsModule {
    @Provides
    fun getRims():Rims{
        println("Rims object provided.....")
        return Rims()
    }

    @Provides
    fun getTyres():Tyres{
        println("Tyres object provided.....")
        return Tyres()
    }

    @Provides
    fun getWheels(rims: Rims, tyres: Tyres):Wheels{
        println("wheels object provided.....")
        return Wheels(rims, tyres)
    }
}