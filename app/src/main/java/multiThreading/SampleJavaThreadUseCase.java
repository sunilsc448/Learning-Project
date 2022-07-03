package multiThreading;

class SampleJavaThreadUseCase {
    public void SampleThreadUseCase() throws InterruptedException {
        SampleJavaThread thread = new SampleJavaThread();
        thread.start();
        //if main thread sleeping while child thread giving notification, main thread has to wait for a lifetime
        //solution for such scenario is use wait with time interval
//        Thread.sleep(11000);

        synchronized (thread){
            System.out.println("main thread going for wait in thread block");
//            thread.wait();
            thread.wait(11000);
            System.out.println("main thread execution in thread block");
        }

//        SampleJavaThread thread2 = new SampleJavaThread();
//        thread2.start();

//        synchronized (thread2){
//            System.out.println("main thread going for wait in thread2 block");
//            thread2.wait(11000);
//            System.out.println("main thread execution in thread2 block");
//        }

//        SampleJavaThread thread3 = new SampleJavaThread();
//        thread3.start();

//        synchronized (thread3){
//            System.out.println("main thread going for wait in thread3 block");
//            thread3.wait(11000);
//            System.out.println("main thread execution in thread3 block");
//        }
    }
}
