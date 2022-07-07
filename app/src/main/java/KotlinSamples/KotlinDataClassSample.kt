package KotlinSamples

class KotlinDataClassSample {
    init {
        //data class works and prints only with the primary constructor values
        val dc1 = ExampleDataClass1("sunil", 33)
        println(dc1) //output: ExampleDataClass1(name=sunil)

        val dc2 = ExampleDataClass2("sunil", 33, "Male")
        println(dc2)//output: ExampleDataClass2(name=sunil, age=33)

        //Equality checks
        val ex1_1 = ExampleDataClass1("sunil", 33)
        val ex1_2 = ExampleDataClass1("anil", 34)

        val ex2_1 = ExampleDataClass2("sunil", 33)
        val ex2_2 = ExampleDataClass2("sunil", 33)

        val ex2_3 = ex2_1

        val isDataClassesSame1 = ex1_1 == ex1_2
        val isDataClassesSame1_ref = ex1_1 === ex1_2
        val isDataClassesSame2 = ex1_2 == ex1_2
        val isDataClassesSame2_ref = ex1_1 === ex1_2
        val isDataClassesSame2_ref_correct = ex2_1 === ex2_3
        println("isDatClassesSame1 > $isDataClassesSame1 & isDatClassesSame2 > $isDataClassesSame2")
        println("isDatClassesSame1_ref > $isDataClassesSame1_ref & isDatClassesSame2_ref > $isDataClassesSame2_ref")
        println("isDatClassesSame2_ref_correct > $isDataClassesSame2_ref_correct")

        //even though the instances are of same values, the classes are not equal
        val exc1 = ExampleClass1("sunil", 33)
        val exc2 = ExampleClass1("sunil", 33)
        val exc3 = exc2
        val isClassesSame = exc1 == exc2
        val isClassesSame_ref = exc1 === exc2
        val isClassesSame_ref_correct = exc2 === exc3
        println("isClassesSame > $isClassesSame")
        println("isClassesSame_ref > $isClassesSame_ref")
        println("isClassesSame_ref_correct > $isClassesSame_ref_correct")

        //more example to demonstrate data class functions only on primary constructor
        val sunilDc = ExampleDataClass1("sunil")
        val sunilCopyDc = ExampleDataClass1("sunil")
        sunilCopyDc.gender = "Male"
        val areSame = sunilDc == sunilCopyDc
        println("areSame $areSame") //returns true because data class functions only on primary constructor

        //one more example to demonstrate data class functions only on primary constructor
        val person = PersonDataClass("sunil", 33, AddressDataClass("hosa road", "Bengaluru", 560035))
        person.gender = "Male"
        println("person details sunil >  $person") // PersonDataClass(name=sunil, age=33, address=AddressDataClass(street=hosa road, city=Bengaluru, pinCode=560035))

        val personCopy = person.copy(name = "pooja", age = 32)
        println("person details pooja >  $personCopy") //PersonDataClass(name=pooja, age=32, address=AddressDataClass(street=hosa road, city=Bengaluru, pinCode=560035))
        println("pooja's gender >  ${personCopy.gender}") //undefined only, data class copy also works only on primary constructor

        //To conclude, properties toString(), ==, copy on data class works only on primary constructor


        /**
          Copy is just a reference copy. Any change in one copy reflects in the other copies
          @author  Sunil
          @version 1.0
          @since   2014-03-31
         */
        //Copy is just a reference copy. Any change in one copy reflects in the other copies
        person.address.street = "kasvanahalli, hosa road"
        println("person details sunil >  $person") //PersonDataClass(name=sunil, age=33, address=AddressDataClass(street=kasvanahalli, hosa road, city=Bengaluru, pinCode=560035))
        println("person details pooja >  $personCopy") // PersonDataClass(name=pooja, age=32, address=AddressDataClass(street=kasvanahalli, hosa road, city=Bengaluru, pinCode=560035))

        personCopy.address.street = "kasvanahalli, hosa road, landmark : HDFC Bank"
        println("person details sunil >  $person") //PersonDataClass(name=sunil, age=33, address=AddressDataClass(street=kasvanahalli, hosa road, landmark : HDFC Bank, city=Bengaluru, pinCode=560035))
        println("person details pooja >  $personCopy") // PersonDataClass(name=pooja, age=32, address=AddressDataClass(street=kasvanahalli, hosa road, landmark : HDFC Bank, city=Bengaluru, pinCode=560035))
        println(personCopy)
    }
}

data class ExampleDataClass1(val name:String) {
    constructor(name: String, age:Int):this(name)
    var gender = "Undefined"
}

data class ExampleDataClass2(val name:String, val age: Int) {
    constructor(name: String, age:Int, gender:String):this(name, age)
}

class ExampleClass1(name: String, age: Int)

data class PersonDataClass(val name: String, val age:Int, val address:AddressDataClass){
    var gender = "undefined"
}

data class AddressDataClass(var street: String, val city:String, val pinCode:Int)