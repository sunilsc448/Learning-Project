package multiThreading

class DeadLockThread1(val deadLockClass1: DeadLockClass1, val deadLockClass2: DeadLockClass2) :Thread(){
    override fun run() {
        super.run()
        deadLockClass1.doSomething(deadLockClass2)
    }
}

class DeadLockThread2(val deadLockClass1: DeadLockClass1,val deadLockClass2: DeadLockClass2) :Thread(){
    override fun run() {
        super.run()
        deadLockClass2.doSomething(deadLockClass1)
    }
}

class DeadLockClass1{
    @Synchronized
    fun doSomething(deadLockClass2: DeadLockClass2){
        println("Thread1 acquired DeadLockClass1 lock and starting")
        try{
            Thread.sleep(2000)
        }catch (e:InterruptedException){ }
        println("Thread1 completed DeadLockClass1 operation")
        println("Thread 1 is acquiring the lock of DeadLockClass2")
        deadLockClass2.print()
    }

    @Synchronized
    fun print(){
        println("Thread 2 acquired DeadLockClass1 and executed print")
    }
}

class DeadLockClass2{
    @Synchronized
    fun doSomething(deadLockClass1: DeadLockClass1){
        println("Thread2 acquired DeadLockClass2 lock and starting")
        try{
            Thread.sleep(2000)
        }catch (e:InterruptedException){ }
        println("Thread2 completed DeadLockClass2 operation")

        println("Thread 2 is acquiring the lock of DeadLockClass1")
        deadLockClass1.print()
    }

    @Synchronized
    fun print(){
        println("Thread 1 acquired DeadLockClass2 and executed print")
    }
}