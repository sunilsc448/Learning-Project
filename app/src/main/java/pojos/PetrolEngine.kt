package pojos

import javax.inject.Inject
import javax.inject.Named

class PetrolEngine:Engine {
    private var mileage:Int
    private var engineCapacity:Int

    @Inject
    constructor(@Named("mileage")mileage:Int, @Named("engineCapacity")engineCapacity:Int){
        this.mileage = mileage
        this.engineCapacity = engineCapacity
    }

    override fun start() {
        println("Petrol Engine Started and mileage is "+mileage+" and engine capacity is "+engineCapacity+".....")
    }
}