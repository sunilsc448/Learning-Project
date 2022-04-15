package pojos

import javax.inject.Inject

class Wheels {
    lateinit var rims: Rims
    lateinit var tyres: Tyres
    constructor(rims: Rims, tyres: Tyres){
        this.rims = rims
        this.tyres = tyres
    }
}