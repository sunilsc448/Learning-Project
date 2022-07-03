package multiThreading

class ThreadProblemStatement1:Thread() {
    var result = 0
    override fun run() {
        super.run()
        for (i in 1..100){
            result += i
        }
    }
}