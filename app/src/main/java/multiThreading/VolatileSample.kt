package multiThreading

class VolatileSample {
    companion object{
        @Volatile
        var MY_INT = 0
    }
    init {
        ChangeListener().start()
        ChangeMaker().start()
    }
}

class ChangeListener:Thread(){
    override fun run() {
        super.run()
        var local_value = VolatileSample.MY_INT
        while (local_value < 5) {
            if (local_value != VolatileSample.MY_INT) {
                local_value = VolatileSample.MY_INT
                println("updated local_value is $local_value")
            }
        }
    }
}

class ChangeMaker:Thread(){
    override fun run() {
        super.run()
        var local_value = VolatileSample.MY_INT
        while (VolatileSample.MY_INT < 5) {
            VolatileSample.MY_INT = ++local_value
            println("incremented VolatileSample.MY_INT to $local_value")
            try {
                sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}