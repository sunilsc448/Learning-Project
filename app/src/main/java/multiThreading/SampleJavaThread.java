package multiThreading;

class SampleJavaThread extends Thread{
    @Override
    public synchronized void run() {
        super.run();
            for (int i = 0; i < 10; i++) {
                System.out.println("child thread "+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("child thread giving notification");
            this.notify();
    }
}
