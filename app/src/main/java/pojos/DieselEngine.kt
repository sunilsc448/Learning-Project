package pojos

import javax.inject.Inject

class DieselEngine:Engine {
    @Inject
    constructor(){}

    override fun start() {
        println("Diesel Engine Started.....")
    }
}