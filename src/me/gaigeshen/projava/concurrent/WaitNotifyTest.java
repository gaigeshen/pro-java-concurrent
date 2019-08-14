package me.gaigeshen.projava.concurrent;

/**
 * @author gaigeshen
 */
public class WaitNotifyTest {


  public static void main(String[] args) throws InterruptedException {

    WaitNotifyTest test = new WaitNotifyTest();

    Thread th1 = new Thread(() -> {
      try {
        test.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread th2 = new Thread(() -> {
      try {
        test.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread th3 = new Thread(() -> {
      test.set();
    });

    th1.start();
    th2.start();

    Thread.sleep(1000);

    th3.start();

    th1.join();
    th2.join();
    th3.join();
  }



  synchronized void get() throws InterruptedException {

    System.out.println("Before wait method " + Thread.currentThread().getName());

    // 被唤醒之后，多个调用了此方法的线程要重新竞争锁
    // 只有重新获得锁的线程能够继续运行下面的代码
    wait();

    System.out.println("Entered get method1 " + Thread.currentThread().getName());

    Thread.sleep(3000);

    System.out.println("Entered get method2 " + Thread.currentThread().getName());

  }


  synchronized void set() {


    notifyAll();
    System.out.println("Notified all threads");

  }


}
