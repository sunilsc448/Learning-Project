package KotlinSamples

class ReifiedInlineCrossLineSample {
    init {
        //Example 1
        printAny("Sunil")
        printAny(122)

        printAnyWithType("Sunil", String::class.java)
        printAnyWithType(122, Int::class.java)

        printAnyReified("Sunil")
        printAnyReified(122)

        //Example 2
        val marks1 = showMarks<Int>(70)
        val marks2 = showMarks<String>(90)
        println("$marks1 and $marks2")

        //Example 3 Inline function
        doSomething()
        doSomethingInline()

        doSomethingLambda()
        doSomethingLambdaInline()

        /*
        when to make the function inline and when not:

        When the function code is very small, it's good idea to make the function inline.
        When the function code is large and called from so many places,
        it's a bad idea to make the function inline,
        as the large code will be repeated again and again.
         */

       //Example 4 No Inline
        doSomethingNoInline()

      //Example 5 Cross line
        doSomethingCrossLine()
    }

    /*
    Error: Overloading can only done for input params and not on return type
    Solution would be a reified keyword with inline function
    fun showMessage(marks: Int): Int {
        return marks
    }
    fun showMessage(marks: Int): String {
        return "Congratulations! you scored more than 90%";
    }
    */

    inline fun<reified T> showMarks(marks:Int):T{
        return when(marks::class){
            Int::class -> marks as T
            String::class -> "Congratulations! you scored distinction" as T
            else -> "Invalid input" as T
        }
    }

    //unable to get the type here
    fun <T> printAny(input:T){
//        println("Type of T: ${T::class.java}")//compile time error: Cannot use T as reified type parameter
        println(input)
    }

    //boiler plate code to get type
    fun <T> printAnyWithType(input:T, classType:Class<T>){
        println("Type of T: ${classType}")//compile time error: Cannot use T as reified type parameter
        println(input)
    }

    inline fun <reified T> printAnyReified(input: T) {
        println("Type of T: ${T::class.java}")
        println(input)
    }

    fun doSomething() {
        print("doSomething start")
        doSomethingElse()
        print("doSomething end")
    }

    fun doSomethingElse() {
        print("doSomethingElse")
    }

    //decompiled Byte Class
    /*
    public void doSomething() {
       System.out.print("doSomething start");
       doSomethingElse();
       System.out.print("doSomething end");
    }

    public void doSomethingElse() {
       System.out.print("doSomethingElse");
    }
    */

    fun doSomethingInline() {
        print("doSomething start")
        doSomethingElseInline()
        print("doSomething end")
    }

    private inline fun doSomethingElseInline() {
        print("doSomethingElse")
    }

    //decompiled Byte Class
    /*
    public void doSomethingInline() {
       System.out.print("doSomething start");
       System.out.print("doSomethingElse")
       System.out.print("doSomething end");
    }
    */

    private fun doSomethingLambda() {
        print("doSomething start")
        doSomethingElseLambda {
            println("doSomethingElse")
        }
        print("doSomething end")
    }

    private fun doSomethingElseLambda(function:() -> Unit) {
        function()
    }

    /*
    public void doSomethingLambda() {
        System.out.print("doSomething start");
        doSomethingElseLambda(new Function() {
            @Override
            public void invoke() {
                System.out.print("doSomethingElse");
            }
        });
        System.out.print("doSomething end");
    }

    public void doSomethingElseLambda(Function abc) {
        abc.invoke();
    }
    */


    private fun doSomethingLambdaInline() {
        print("doSomething start")
        doSomethingElseLambdaInline {
            println("doSomethingElse")
        }
        print("doSomething end")
    }

    private inline fun doSomethingElseLambdaInline(function: () -> Unit) {
        function()
    }

    /*
    public void doSomethingLambdaInline() {
        System.out.print("doSomething start");
        System.out.print("doSomethingElse");
        System.out.print("doSomething end");
    }
    */

    private fun doSomethingNoInline() {
        print("doSomething start")
        doSomethingNoInline({
            println("doSomething with abc")
            },{
                println("doSomething with xyz")
            })
    }

    private inline fun doSomethingNoInline(abc:()->Unit, noinline xyz:()-> Unit){
        abc()
        xyz()
    }

    fun doSomethingCrossLine() {
        print("doSomething start")
        doSomethingElseCrossLine{
            print("doSomethingElse")
            return // notice this return
        }
        print("doSomething end")
    }

    inline fun doSomethingElseCrossLine(abc: () -> Unit) {
        abc()
    }

    /*print("doSomething end") is missing here because of return statement
    Solution is crossinline
    public void doSomethingCrossLine() {
        System.out.print("doSomething start");
        System.out.print("doSomethingElse");
    }
    */

    inline fun doSomethingElseCrossLineSoultion(crossinline abc: () -> Unit) {
        abc()
    }

    /*print("doSomething end") is missing here because of return statement
    Solution is crossinline
    public void doSomethingCrossLine() {
        System.out.print("doSomething start");
        System.out.print("doSomethingElse");
        System.out.print("doSomething end");
    }
    */
}