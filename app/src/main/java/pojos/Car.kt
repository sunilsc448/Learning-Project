package pojos

import pojos.Driver
import javax.inject.Inject

class Car {
    @Inject
    lateinit var engine: Engine
    lateinit var driver: Driver
    private var wheels: Wheels

    @Inject
    constructor(wheels: Wheels, driver: Driver){
        this.wheels = wheels
        this.driver = driver
    }

    fun start(){
        engine.start()
        println("driver address....."+driver)
        println("car is starting.....")
    }

    @Inject
    fun provideCarToRemote(remote: Remote){
        remote.provideCar(this)
    }
}