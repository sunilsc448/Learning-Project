package pojos

open class SecondaryConstructor(_firstName:String) {
    var firstName = "first name"
    var secondName = "second name"
    init {
        firstName = _firstName
    }
    constructor(_firstName: String, _secondName:String):this(_firstName){
        secondName = _secondName
        println("the firsnt name is $firstName and second name is $secondName")
    }
}