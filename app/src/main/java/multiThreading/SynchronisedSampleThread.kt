package multiThreading

class SyncronisedSampleThread(val syncronisedSampleClass: SynchronisedSampleClass, val displayName:String):Thread() {
    override fun run() {
        super.run()

//        syncronisedSampleClass.hello(displayName)

        syncronisedSampleClass.displayName(displayName)

        //to execute static methods, thread acquires class level lock,
        // so whichever thread acquire class level lock first, it will execute. Her we will get regular output
//        SynchronisedSampleClass.displayNameStatic(displayName)
    }
}

class SynchronisedSampleClass{
     @Synchronized
     fun displayName(name:String){
        for (i in 1..10){
            println("Good Morning: $name")
            try {
                Thread.sleep(500)
            }catch (e:InterruptedException){ }
        }
    }

    companion object {
        @Synchronized
        fun displayNameStatic(name: String) {
            for (i in 1..10){
                println("Good Morning: $name")
                try {
                    Thread.sleep(500)
                }catch (e:InterruptedException){ }
            }
        }
    }

    fun hello(name: String){
        for (i in 1..10){
            println("Hello: $i $name")
        }
    }
}

class SynchronisedDiaplyThread1(val display:SynchronisedDisplay):Thread(){
    override fun run() {
        super.run()
        display.displayNumber()
    }
}

class SynchronisedDiaplyThread2(val display:SynchronisedDisplay):Thread(){
    override fun run() {
        super.run()
        display.displayCharacter()
    }
}

class SynchronisedDisplay{
    @Synchronized
    fun displayNumber(){
        for (i in 1..10){
            println("Number is: $i")
            try {
                Thread.sleep(500)
            }catch (e:InterruptedException){ }
        }
    }

    @Synchronized
    fun displayCharacter(){
        for (char in 'A'..'Z'){
            println("Character is: $char")
            try {
                Thread.sleep(200)
            }catch (e:InterruptedException){ }
        }
    }
}

