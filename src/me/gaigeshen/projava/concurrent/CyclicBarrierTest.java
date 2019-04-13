package me.gaigeshen.projava.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏
 *
 * @author gaigeshen
 */
public class CyclicBarrierTest {

  public static void main(String[] args) {

    CyclicBarrier barrier1 = new CyclicBarrier(2, () -> {
      System.out.println("Action1...");
    });

    CyclicBarrier barrier2 = new CyclicBarrier(2, () -> {
      System.out.println("Action2...");
    });

    CyclicBarrierRunnable runnable1 = new CyclicBarrierRunnable(barrier1, barrier2);
    CyclicBarrierRunnable runnable2 = new CyclicBarrierRunnable(barrier1, barrier2);

    new Thread(runnable1).start();
    new Thread(runnable2).start();
  }

}

class CyclicBarrierRunnable implements Runnable {

  private CyclicBarrier barrier1;
  private CyclicBarrier barrier2;

  CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
    this.barrier1 = barrier1;
    this.barrier2 = barrier2;
  }

  @Override
  public void run() {

    try {
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + " waiting at barrier1");
      barrier1.await();

      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + " waiting at barrier2");
      barrier2.await();

      System.out.println(Thread.currentThread().getName() + " done");
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }

  }

}
