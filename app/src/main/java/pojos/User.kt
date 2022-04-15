package pojos

class User(var name_: String): Player("", 0) {
    init
    {
        println("Student has got a name as $name_")
    }
}