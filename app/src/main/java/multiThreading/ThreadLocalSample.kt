package multiThreading

class ThreadLocalSample {
    init {
//        basics()
        threadLocalSample1()
    }

    private fun threadLocalSample1() {
        val thread1 = CustomerThread("Sunil")
        val thread2 = CustomerThread("Anil")
        val thread3 = CustomerThread("Vijay")
        val thread4 = CustomerThread("Raju")
        thread1.start()
        thread2.start()
        thread3.start()
        thread4.start()
    }

    private fun basics() {
        val tl = ThreadLocal<String>()
        println("current thread ${Thread.currentThread().name}") //null
        println("default thread local variable of current thread is ${tl.get()}") //null
        tl.set("Sunil")
        println("updated thread local variable of current thread is ${tl.get()}") //null
        tl.remove()
        println("updated thread local variable of current thread is ${tl.get()}") //null

        val tl1 = object:ThreadLocal<Int>(){
            override fun initialValue(): Int? {
                return 100
            }
        }
        println("current thread ${Thread.currentThread().name}") //null
        println("default thread local variable 1 of current thread is ${tl1.get()}") //100
    }
}

class CustomerThread(private val displayName:String):Thread(){
    companion object{
        val threadLocal = object:InheritableThreadLocal<Int>(){
            override fun childValue(parentValue: Int?): Int {
                return 202
            }
        }
    }

    override fun run() {
        super.run()
        threadLocal.set(101)
        println("$displayName's ${currentThread().name} started and it's ThreadLocal CustomerID is ${threadLocal.get()}")
        val childThread = ConsumerChildThread()
        childThread.start()
    }
}

class ConsumerChildThread:Thread(){
    override fun run() {
        super.run()
        println("CustomerChild Thread ${CustomerThread.threadLocal.get()}") //null, because the parent's thread local
    // value won't be available to child
    }
}