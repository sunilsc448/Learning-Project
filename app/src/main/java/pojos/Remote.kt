package pojos

import javax.inject.Inject

class Remote {
    lateinit var car: Car
    @Inject
    constructor(){}

    fun provideCar(car: Car){
        println("Providing car to remote.....")
        this.car = car
    }
}