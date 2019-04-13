package me.gaigeshen.projava.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 *
 * @author gaigeshen
 */
public class CountDownLatchTest {
  public static void main(String[] args) {

    CountDownLatch latch = new CountDownLatch(3);

    Waiter waiter = new Waiter(latch);
    Decrementer decrementer = new Decrementer(latch);

    new Thread(waiter).start();
    new Thread(decrementer).start();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

class Waiter implements Runnable {

  CountDownLatch latch = null;

  Waiter(CountDownLatch latch) {
    this.latch = latch;
  }

  @Override
  public void run() {
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("I am waiter");
  }
}

class Decrementer implements Runnable {

  CountDownLatch latch;

  Decrementer(CountDownLatch latch) {
    this.latch = latch;
  }

  @Override
  public void run() {

    try {
      Thread.sleep(1000);
      this.latch.countDown();
      System.out.println("one...");

      Thread.sleep(1000);
      this.latch.countDown();
      System.out.println("two...");

      Thread.sleep(1000);
      this.latch.countDown();
      System.out.println("three...");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
